package org.wispay.payment.sdk.mservice.model.response;

public class RefundResponse {
    String requestId;

    String billId;

    String refundBillId;

    String pspRefId;

    String pspCode;

    private String transId;

    private Double amount;

    public RefundResponse() {

    }

    public RefundResponse(String transId, Double amount, String pspCode, String pspRefId, String refundBillId, String billId, String requestId) {
        this.transId = transId;
        this.amount = amount;
        this.pspCode = pspCode;
        this.pspRefId = pspRefId;
        this.refundBillId = refundBillId;
        this.billId = billId;
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
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

    public String getPspCode() {
        return pspCode;
    }

    public void setPspCode(String pspCode) {
        this.pspCode = pspCode;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}