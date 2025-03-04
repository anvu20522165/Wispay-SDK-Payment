package org.wispay.payment.sdk.mservice.processor;

import org.wispay.payment.sdk.mservice.common.exception.WisPayException;
import org.wispay.payment.sdk.mservice.common.utils.EncodeUtils;
import org.wispay.payment.sdk.mservice.common.utils.ErrorUtils;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.request.QueryBillRequest;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;
import org.wispay.payment.sdk.mservice.model.response.QueryResponse;

import java.util.HashMap;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;
import static org.wispay.payment.sdk.mservice.common.enums.ResultCode.INVALID_DATA;

public class QueryBillProcessor extends AbstractProcess<QueryBillRequest, QueryResponse> {

    public QueryBillProcessor(Environment environment) {
        super(environment);
    }

    public static QueryResponse process(Environment environment, QueryBillRequest request) {
        QueryBillProcessor queryBillProcessor = new QueryBillProcessor(environment);
        return queryBillProcessor.execute(request);
    }

    @Override
    public QueryResponse execute(QueryBillRequest request) {
        HttpRequest httpRequest = createQueryBillRequest(request);
        HttpResponse response = executor.sendToWisPay(httpRequest);
        if (response.getStatusCode() != 200) {
            ErrorUtils.extractErrorDetails(response.getPayload());
        }
        QueryResponse queryResponse = getGson().fromJson(response.getData(), QueryResponse.class);
        log("Getting Bill Process", queryResponse);
        return queryResponse;
    }

    public HttpRequest createQueryBillRequest(QueryBillRequest request) {
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

            return new HttpRequest(
                    POST,
                    environment.getWisPayEndpoint().getQueryBill(),
                    payload,
                    APPLICATION_JSON,
                    headers,
                    request.getLang());
        } catch (Exception e) {
            throw new WisPayException(INVALID_DATA.getMsgCode(), INVALID_DATA.getCode(), e.getMessage());
        }

    }
}
