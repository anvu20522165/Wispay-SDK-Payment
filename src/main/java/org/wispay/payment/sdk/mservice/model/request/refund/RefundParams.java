package org.wispay.payment.sdk.mservice.model.request.refund;

import org.wispay.payment.sdk.mservice.model.bill.extra.BillItem;
import org.wispay.payment.sdk.mservice.model.request.Request;

import java.util.ArrayList;
import java.util.List;

public class RefundParams extends Request {
    String description;

    String refundBillId;

    String pspRefId;
    List<BillItem> items = new ArrayList<>();
    private Double amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefundBillId() {
        return refundBillId;
    }

    public void setRefundBillId(String refundBillId) {
        this.refundBillId = refundBillId;
    }

    public String getPspRefId() {
        return pspRefId;
    }

    public void setPspRefId(String pspRefId) {
        this.pspRefId = pspRefId;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }
}