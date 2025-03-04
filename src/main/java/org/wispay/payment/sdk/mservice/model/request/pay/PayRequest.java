package org.wispay.payment.sdk.mservice.model.request.pay;

import org.wispay.payment.sdk.mservice.model.request.Request;

public class PayRequest extends Request {

    private Payment data;
    private PaymentOptions paymentOptions = new PaymentOptions();

    public Payment getData() {
        return data;
    }

    public void setData(Payment data) {
        this.data = data;
    }

    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(PaymentOptions paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public static class Payment {

        private Integer providerId;
        private String paymentMethod;

        public Payment(Integer providerId, String paymentMethod) {
            this.providerId = providerId;
            this.paymentMethod = paymentMethod;
        }

        public Payment() {

        }

        public Integer getProviderId() {
            return providerId;
        }

        public void setProviderId(Integer providerId) {
            this.providerId = providerId;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }
}
