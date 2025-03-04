package org.wispay.payment.sdk.mservice.model.response;

public class QueryRefundResponse {
    String requestId;
    String billId;
    String refundBillId;
    Object refundList;

    public QueryRefundResponse() {

    }

    public QueryRefundResponse(String billId, String refundBillId, Object refundList, String requestId) {
        this.billId = billId;
        this.refundBillId = refundBillId;
        this.refundList = refundList;
        this.requestId = requestId;
    }
}