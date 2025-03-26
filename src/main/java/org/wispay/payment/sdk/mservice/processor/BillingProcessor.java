package org.wispay.payment.sdk.mservice.processor;

import com.google.gson.reflect.TypeToken;
import org.wispay.payment.sdk.mservice.common.exception.WisPayException;
import org.wispay.payment.sdk.mservice.common.utils.EncodeUtils;
import org.wispay.payment.sdk.mservice.common.utils.ErrorUtils;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.model.bill.extra.BillItem;
import org.wispay.payment.sdk.mservice.model.bill.extra.Customer;
import org.wispay.payment.sdk.mservice.model.bill.extra.Shipping;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.request.PurchaseRequest;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;
import org.wispay.payment.sdk.mservice.model.response.PurchaseResponse;
import org.wispay.payment.sdk.mservice.model.response.wispay.WisPayResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;
import static org.wispay.payment.sdk.mservice.common.enums.ResultCode.INVALID_DATA;

public class BillingProcessor extends AbstractProcess<PurchaseRequest, WisPayResponse<PurchaseResponse>> {

    public BillingProcessor(Environment environment) {
        super(environment);
    }

    public static WisPayResponse<PurchaseResponse> process(Environment environment, PurchaseRequest purchaseRequest) {
        BillingProcessor processor = new BillingProcessor(environment);
        return processor.execute(purchaseRequest);
    }

    @Override
    public WisPayResponse<PurchaseResponse> execute(PurchaseRequest request) {

        HttpRequest httpRequest = createPaymentRequest(request);
        HttpResponse response = executor.sendToWisPay(httpRequest);
        if (response.getStatusCode() != 200) {
            ErrorUtils.extractErrorDetails(response.getPayload());
        }

        Type type = new TypeToken<WisPayResponse<PurchaseResponse>>() {
        }.getType();
        WisPayResponse<PurchaseResponse> purchaseResponse = getGson().fromJson(response.getPayload(), type);
        log("Creating Bill Process", purchaseResponse);
        return purchaseResponse;
    }

    public HttpRequest createPaymentRequest(PurchaseRequest request) {
        try {
            request.getBill().setCustomer(getGson().fromJson(request.getCustomer(), Customer.class));
            request.getBill().setShipping(getGson().fromJson(request.getShipping(), Shipping.class));
            request.getBill().setRequestId(request.getRequestId());
            request.getBill().setBillId(request.getBillId());

            if (request.getItems() != null && !request.getItems().isEmpty()) {
                List<BillItem> items = new ArrayList<>();
                for (String item : request.getItems()) {
                    BillItem convertedItem = getGson().fromJson(item, BillItem.class);
                    items.add(convertedItem);
                }
                request.getBill().setItems(items);
            }
            request.getBill().setCallbackURL(request.getCallbackURL());
            request.getBill().setPostbackURL(request.getPostbackURL());

            String payload = getGson().toJson(request.getBill());
            String apiKey = partnerInfo.getApiKey();
            String rawData = request.getTimestamp() + apiKey + request.getRecvWindow() + payload;
            String signature = EncodeUtils.signHmacSHA512(rawData, partnerInfo.getSecretKey());

            Map<String, Object> headers = new HashMap<>();
            headers.put(X_API_KEY, apiKey);
            headers.put(TIMESTAMP, request.getTimestamp());
            headers.put(SIGNATURE, signature);
            headers.put(RECEIVE_WINDOW, request.getRecvWindow());
            headers.put(CONTENT_TYPE, APPLICATION_JSON);

            return new HttpRequest(
                    POST,
                    environment.getWisPayEndpoint().getCreate(),
                    payload,
                    APPLICATION_JSON,
                    headers,
                    request.getLang());
        } catch (Exception e) {
            throw new WisPayException(INVALID_DATA.getMsgCode(), INVALID_DATA.getCode(), e.getMessage());
        }
    }
}
