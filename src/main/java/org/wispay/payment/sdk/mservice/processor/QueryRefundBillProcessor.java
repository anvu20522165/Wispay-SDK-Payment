package org.wispay.payment.sdk.mservice.processor;

import com.google.gson.reflect.TypeToken;
import org.wispay.payment.sdk.mservice.common.exception.WisPayException;
import org.wispay.payment.sdk.mservice.common.utils.EncodeUtils;
import org.wispay.payment.sdk.mservice.common.utils.ErrorUtils;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.request.QueryRefundRequest;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;
import org.wispay.payment.sdk.mservice.model.response.QueryRefundResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;
import static org.wispay.payment.sdk.mservice.common.enums.ResultCode.INVALID_DATA;

public class QueryRefundBillProcessor extends AbstractProcess<QueryRefundRequest, WisPayResponse<QueryRefundResponse>> {

    public QueryRefundBillProcessor(Environment environment) {
        super(environment);
    }

    public static WisPayResponse<QueryRefundResponse> process(Environment environment, final QueryRefundRequest request) {
        QueryRefundBillProcessor processor = new QueryRefundBillProcessor(environment);
        WisPayResponse<QueryRefundResponse> response = processor.execute(request);
        return response;
    }

    @Override
    public WisPayResponse<QueryRefundResponse> execute(QueryRefundRequest request) {
        HttpRequest httpRequest = createQueryRefundRequest(request);
        HttpResponse response = executor.sendToWisPay(httpRequest);
        if (response.getStatusCode() != 200) {
            ErrorUtils.extractErrorDetails(response.getPayload());
        }
        Type type = new TypeToken<WisPayResponse<QueryRefundResponse>>() {
        }.getType();
        WisPayResponse<QueryRefundResponse> queryRefundResponse = getGson().fromJson(response.getPayload(), type);
        log("Getting Refund Bill Process", queryRefundResponse);

        return queryRefundResponse;
    }

    private HttpRequest createQueryRefundRequest(QueryRefundRequest request) {

        try {
            String payload = getGson().toJson(request);
            String apiKey = partnerInfo.getApiKey();
            String rawData = request.getTimestamp() + apiKey + request.getRecvWindow() + payload;
            String signature = EncodeUtils.signHmacSHA512(rawData, partnerInfo.getSecretKey());

            Map<String, Object> headers = new HashMap<>();
            headers.put(X_API_KEY, apiKey);
            headers.put(TIMESTAMP, request.getTimestamp());
            headers.put(SIGNATURE, signature);
            headers.put(RECEIVE_WINDOW, request.getRecvWindow());
            headers.put(CONTENT_TYPE, APPLICATION_JSON);

            HttpRequest httpRequest = new HttpRequest(
                    POST,
                    environment.getWisPayEndpoint().getQueryRefund(),
                    payload,
                    APPLICATION_JSON,
                    headers,
                    request.getLang());
            return httpRequest;
        } catch (Exception e) {
            throw new WisPayException(INVALID_DATA.getMsgCode(), INVALID_DATA.getCode(), e.getMessage());
        }
    }
}
