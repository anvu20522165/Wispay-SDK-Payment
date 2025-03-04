package org.wispay.payment.sdk.execute;

import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.Request;
import org.wispay.payment.sdk.mservice.model.response.PaymentMethod;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;
import org.wispay.payment.sdk.mservice.processor.PaymentMethodProcessor;

import java.util.List;

import static org.wispay.payment.sdk.mservice.common.constant.WisPayConstant.LOCAL;

public class GetPaymentMethods {

    public static void main(String[] args) {
        Environment environment = Environment.selectEnv(LOCAL);
        Request request = new Request();
        request.setRecvWindow(100000L);
        request.setTimestamp(System.currentTimeMillis());
        WisPayResponse<List<PaymentMethod>> response = PaymentMethodProcessor.process(environment, request);
    }
}
