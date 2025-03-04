/*
package org.wispay.payment.sdk.execute;

import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.QueryRefundRequest;
import org.wispay.payment.sdk.mservice.model.response.QueryRefundResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;
import org.wispay.payment.sdk.mservice.processor.QueryRefundBillProcessor;

import static org.wispay.payment.sdk.mservice.common.constant.WisPayConstant.LOCAL;

public class QueryRefundBill {

    public static void main(String[] args) {
        QueryRefundRequest request = new QueryRefundRequest();
        request.setRequestId("17243823071995");
        request.setBillId("SDKTest001");
        request.setRecvWindow(100000L);
        request.setTimestamp(System.currentTimeMillis());
        request.setRefundBillId("SDKRefundTest002");
        request.setUnpaged(false);
        request.setPageNumber(1);
        request.setPageSize(1);

        Environment environment = Environment.selectEnv(LOCAL);
        WisPayResponse<QueryRefundResponse> payResponse = QueryRefundBillProcessor.process(environment, request);
    }
}
*/
