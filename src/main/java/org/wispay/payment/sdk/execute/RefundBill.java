package org.wispay.payment.sdk.execute;

import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.request.RefundRequest;
import org.wispay.payment.sdk.mservice.model.request.refund.RefundParams;
import org.wispay.payment.sdk.mservice.model.response.RefundResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;
import org.wispay.payment.sdk.mservice.processor.RefundProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.wispay.payment.sdk.mservice.common.constant.WisPayConstant.LOCAL;

public class RefundBill {
    public static void main(String[] args) {
        RefundParams params = new RefundParams();
        params.setRequestId("17243823071995");
        params.setBillId("SDKTest001");
        params.setRecvWindow(100000L);
        params.setTimestamp(System.currentTimeMillis());
        params.setDescription("Test refund 01");
        params.setRefundBillId("SDKRefundTest002");
        params.setAmount(1000.0);
        params.setTransId("14668");

        //Item list
        String item1 = "        {\n" +
                "            \"listingPrice\": 125000,\n" +
                "            \"name\": \"full luồng zalopay\",\n" +
                "            \"discountAmount\": 20,\n" +
                "            \"currency\": \"VND\",\n" +
                "            \"quantity\": 1,\n" +
                "            \"id\": \"8fa50f7f\",\n" +
                "            \"sellingPrice\": 100000,\n" +
                "            \"total\": 100000,\n" +
                "            \"amount\": 100000\n" +
                "        }";


        List<String> itemList = new ArrayList<>();
        itemList.add(item1);

        RefundRequest request = new RefundRequest();
        request.setRefund(params);
//        request.setItems(itemList);

        Environment environment = Environment.selectEnv(LOCAL);
        WisPayResponse<RefundResponse> wisPayResponse = RefundProcessor.process(environment, request);

    }
}
