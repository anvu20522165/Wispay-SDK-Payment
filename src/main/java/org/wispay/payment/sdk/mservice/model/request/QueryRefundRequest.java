package org.wispay.payment.sdk.mservice.model.request;

public class QueryRefundRequest extends Request {

    String refundBillId;

    Integer pageSize;

    Integer pageNumber;

    boolean unpaged;

    public String getRefundBillId() {
        return refundBillId;
    }

    public void setRefundBillId(String refundBillId) {
        this.refundBillId = refundBillId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isUnpaged() {
        return unpaged;
    }

    public void setUnpaged(boolean unpaged) {
        this.unpaged = unpaged;
    }
}