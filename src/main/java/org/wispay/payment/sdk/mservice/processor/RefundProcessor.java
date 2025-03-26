package org.wispay.payment.sdk.mservice.processor;

import com.google.gson.reflect.TypeToken;
import org.wispay.payment.sdk.mservice.common.exception.WisPayException;
import org.wispay.payment.sdk.mservice.common.utils.EncodeUtils;
import org.wispay.payment.sdk.mservice.common.utils.ErrorUtils;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.bill.extra.BillItem;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.request.RefundRequest;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;
import org.wispay.payment.sdk.mservice.model.response.RefundResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;
import static org.wispay.payment.sdk.mservice.common.enums.ResultCode.INVALID_DATA;

public class RefundProcessor extends AbstractProcess<RefundRequest, WisPayResponse<RefundResponse>> {

    public RefundProcessor(Environment environment) {
        super(environment);
    }


    public static WisPayResponse<RefundResponse> process(Environment environment, RefundRequest refundRequest) {
        RefundProcessor processor = new RefundProcessor(environment);
        return processor.execute(refundRequest);
    }

    @Override
    public WisPayResponse<RefundResponse> execute(RefundRequest request) {
        HttpRequest httpRequest = createRefundRequest(request);
        HttpResponse response = executor.sendToWisPay(httpRequest);
        if (response.getStatusCode() != 200) {
            ErrorUtils.extractErrorDetails(response.getPayload());
        }
        Type type = new TypeToken<WisPayResponse<RefundResponse>>() {
        }.getType();
        WisPayResponse<RefundResponse> refundResponse = getGson().fromJson(response.getPayload(), type);
        log("Refunding Bill Process", refundResponse);
        return refundResponse;
    }

    private HttpRequest createRefundRequest(RefundRequest request) {
        try {
            if (request.getItems().isEmpty() && request.getItems() != null) {
                List<BillItem> items = new ArrayList<>();
                for (String item : request.getItems()) {
                    BillItem convertedItem = getGson().fromJson(item, BillItem.class);
                    items.add(convertedItem);
                }
                request.getRefund().setItems(items);
            }

            String payload = getGson().toJson(request.getRefund());
            String apiKey = partnerInfo.getApiKey();
            long recvWindow = request.getRefund().getRecvWindow();
            long timestamp = request.getRefund().getTimestamp();
            String rawData = timestamp + apiKey + recvWindow + payload;
            String signature = EncodeUtils.signHmacSHA512(rawData, partnerInfo.getSecretKey());

            Map<String, Object> headers = new HashMap<>();
            headers.put(X_API_KEY, apiKey);
            headers.put(TIMESTAMP, timestamp);
            headers.put(SIGNATURE, signature);
            headers.put(RECEIVE_WINDOW, recvWindow);
            headers.put(CONTENT_TYPE, APPLICATION_JSON);

            return new HttpRequest(
                    POST,
                    environment.getWisPayEndpoint().getRefund(),
                    payload,
                    APPLICATION_JSON,
                    headers,
                    request.getRefund().getLang());
        } catch (Exception e) {
            throw new WisPayException(INVALID_DATA.getMsgCode(), INVALID_DATA.getCode(), e.getMessage());
        }
    }
}
