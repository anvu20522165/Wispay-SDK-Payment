package org.wispay.payment.sdk.mservice.model.request;

import org.wispay.payment.sdk.mservice.model.request.refund.RefundParams;

import java.util.ArrayList;
import java.util.List;

public class RefundRequest {
    RefundParams refund;

    List<String> items = new ArrayList<>();

    public RefundParams getRefund() {
        return refund;
    }

    public void setRefund(RefundParams refund) {
        this.refund = refund;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}