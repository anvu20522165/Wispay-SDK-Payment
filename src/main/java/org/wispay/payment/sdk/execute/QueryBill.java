package org.wispay.payment.sdk.execute;

import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.QueryBillRequest;
import org.wispay.payment.sdk.mservice.model.response.QueryResponse;
import org.wispay.payment.sdk.mservice.processor.QueryBillProcessor;

import static org.wispay.payment.sdk.mservice.common.constant.WisPayConstant.LOCAL;

public class QueryBill {
    public static void main(String[] args) {
        QueryBillRequest request = new QueryBillRequest();
        request.setRequestId("17243823071995");
        request.setBillId("SDKTest001");
        request.setRecvWindow(100000L);
        request.setTimestamp(System.currentTimeMillis());
        request.setFetchTrans(true);
        request.setFetchRefundTrans(true);
        Environment environment = Environment.selectEnv(LOCAL);
        QueryResponse response = QueryBillProcessor.process(environment, request);
    }
}
