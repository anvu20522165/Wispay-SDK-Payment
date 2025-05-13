package org.wispay.payment.sdk.execute;

import org.wispay.payment.sdk.mservice.common.enums.Language;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.bill.BillParams;
import org.wispay.payment.sdk.mservice.model.bill.extra.PaymentUrlConfig;
import org.wispay.payment.sdk.mservice.model.request.PurchaseRequest;
import org.wispay.payment.sdk.mservice.model.response.PurchaseResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;
import org.wispay.payment.sdk.mservice.processor.BillingProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.wispay.payment.sdk.mservice.common.constant.WisPayConstant.DEV;

public class CreateBill {
    public static void main(String[] args) {
        String requestId = String.valueOf(System.currentTimeMillis());
        Double amount = 50000.0;
        String item1 = " {"
                + "        \"listingPrice\": 125000,"
                + "        \"name\": \"Bóp da\","
                + "        \"discountAmount\": 20000,"
                + "        \"currency\": \"VND\","
                + "        \"quantity\": 1,"
                + "        \"id\": \"8fa50f7f\","
                + "        \"sellingPrice\": 100000,"
                + "        \"total\": 100000"
                + "    }";

        String item2 = " {"
                + "        \"listingPrice\": 125000,"
                + "        \"name\": \"Quần đùi\","
                + "        \"discountAmount\": 15000,"
                + "        \"currency\": \"VND\","
                + "        \"quantity\": 1,"
                + "        \"id\": \"8fa50f7f\","
                + "        \"sellingPrice\": 100000,"
                + "        \"total\": 100000"
                + "    }";

        String customer = "{\n" +
                "        \"firstName\": \"Hưng\",\n" +
                "        \"lastName\": \"Đàm\",\n" +
                "        \"email\": \"damvinhhung@gmail.com\",\n" +
                "        \"phoneNumber\": \"0348067138\"\n" +
                "    }";

        String shipping = "{\n" +
                "        \"address1\": \"Số 1 công trường Công xã Paris, phường Bến Nghé, quận 1, tp. Hồ Chí Minh\",\n" +
                "        \"carrierType\": \"Car\",\n" +
                "        \"description\": \"hassle free\"\n" +
                "    }";

        List<String> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setRequestId(requestId);
        //purchaseRequest.setShipping(shipping);
        //purchaseRequest.setCustomer(customer);
        //purchaseRequest.setItems(itemList);
        purchaseRequest.setTimestamp(System.currentTimeMillis());
        purchaseRequest.setRecvWindow(100000L);
        purchaseRequest.setBillId("WP" + System.currentTimeMillis());
        purchaseRequest.setCallbackURL("https://google.com");
        purchaseRequest.setPostbackURL("https://google.com");
        purchaseRequest.setLang(Language.EN);


        BillParams billParams = new BillParams();

        //Configure for custom payment url - OPTIONAL
        PaymentUrlConfig paymentUrlConfig = new PaymentUrlConfig();
        String[] pspCodes = {"acb", "zalopay"};
        paymentUrlConfig.setPspCodes(pspCodes);
        paymentUrlConfig.setDirect(false);
        paymentUrlConfig.setCancelable(false);

        billParams.setDescription("Creating Bill - Test");
        billParams.setAmount(amount);
        billParams.setPaymentAmount(5000.0);
        billParams.setCurrency("VND");
        billParams.setExpiryAt(System.currentTimeMillis() + 900000); // + 15 min
        billParams.setExtraData(new HashMap<>());
        billParams.setPaymentUrlConfig(paymentUrlConfig);

        //Bill
        purchaseRequest.setBill(billParams);

        //Set env
        Environment environment = Environment.selectEnv(DEV);
        WisPayResponse<PurchaseResponse> purchaseResponse = BillingProcessor.process(environment, purchaseRequest);

    }
}
