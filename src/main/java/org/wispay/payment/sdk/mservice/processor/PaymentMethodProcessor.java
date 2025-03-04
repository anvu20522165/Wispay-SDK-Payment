package org.wispay.payment.sdk.mservice.processor;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.wispay.payment.sdk.mservice.common.utils.EncodeUtils;
import org.wispay.payment.sdk.mservice.common.utils.ErrorUtils;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.request.Request;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;
import org.wispay.payment.sdk.mservice.model.response.PaymentMethod;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;

public class PaymentMethodProcessor extends AbstractProcess<Request, WisPayResponse<List<PaymentMethod>>> {

    public PaymentMethodProcessor(Environment environment) {
        super(environment);
    }

    public static WisPayResponse<List<PaymentMethod>> process(Environment environment, Request request) {
        PaymentMethodProcessor paymentMethodProcessor = new PaymentMethodProcessor(environment);
        WisPayResponse<List<PaymentMethod>> response = paymentMethodProcessor.execute(request);
        return response;
    }

    @Override
    public WisPayResponse<List<PaymentMethod>> execute(Request request) {
        HttpRequest httpRequest = createPaymentMethodRequest(request);
        HttpResponse response = executor.sendToWisPay(httpRequest);
        if (response.getStatusCode() != 200) {
            ErrorUtils.extractErrorDetails(response.getPayload());
        }

        JsonElement dataElement = response.getData();
        if (dataElement == null || !dataElement.isJsonArray()) {
            throw new IllegalArgumentException("Invalid response structure, expected JSON array in 'data'");
        }

        Type type = new TypeToken<WisPayResponse<List<PaymentMethod>>>() {
        }.getType();
        WisPayResponse<List<PaymentMethod>> wisPayResponse = getGson().fromJson(response.getPayload(), type);

        log("Getting Payment Methods Process", wisPayResponse);

        return wisPayResponse;
    }

    public HttpRequest createPaymentMethodRequest(Request request) {
        String payload = "";
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
                GET,
                environment.getWisPayEndpoint().getPaymentMethod(),
                payload,
                APPLICATION_JSON,
                headers,
                request.getLang());
    }
}
