package org.wispay.payment.sdk.mservice.model.request;

import java.io.Serializable;

public class QueryBillRequest extends Request implements Serializable {

    private boolean fetchTrans;

    private boolean fetchRefundTrans;

    public boolean isFetchTrans() {
        return fetchTrans;
    }

    public void setFetchTrans(boolean fetchTrans) {
        this.fetchTrans = fetchTrans;
    }

    public boolean isFetchRefundTrans() {
        return fetchRefundTrans;
    }

    public void setFetchRefundTrans(boolean fetchRefundTrans) {
        this.fetchRefundTrans = fetchRefundTrans;
    }
}
