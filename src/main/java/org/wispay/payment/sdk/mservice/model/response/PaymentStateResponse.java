package org.wispay.payment.sdk.mservice.model.response;

import com.fasterxml.jackson.annotation.JsonValue;

public class PaymentStateResponse {

    private String data;

    public String getData() {
        return data;
    }

    public enum PaymentState {
        NOT_PAID("not-paid-yet"),
        PARTIALLY_PAID("partially-paid"),
        FULLY_PAID("fully-paid"),
        PARTIALLY_REFUND("partially-refund"),
        FULLY_REFUND("fully-refund");
        @JsonValue
        final String paymentState;

        PaymentState(String paymentState) {
            this.paymentState = paymentState;
        }

        public static PaymentState findByState(String state) {
            for (PaymentState v : values()) {
                if (v.paymentState.toLowerCase().equals(state)) {
                    return v;
                }
            }
            return null;
        }
    }
}
