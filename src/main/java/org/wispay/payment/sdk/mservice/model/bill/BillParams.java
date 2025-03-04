package org.wispay.payment.sdk.mservice.model.bill;

import org.wispay.payment.sdk.mservice.model.Attribute;
import org.wispay.payment.sdk.mservice.model.bill.extra.BillItem;
import org.wispay.payment.sdk.mservice.model.bill.extra.Customer;
import org.wispay.payment.sdk.mservice.model.bill.extra.Shipping;

import java.util.List;
import java.util.Map;

public class BillParams {

    private String requestId;

    private String billId;

    private List<BillItem> items;

    private String description;

    private Double amount;

    private Double partialAmount;

    private String currency = "VND";

    private Customer customer;

    private Shipping shipping;

    private List<Attribute> attributes;

    private Map<String, String> extraData;

    private Long expiryAt;

    private String template;

    private String callbackURL;

    private String postbackURL;

    private String pspCode;


    public BillParams() {
        super();
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

    public String getPspCode() {
        return pspCode;
    }

    public void setPspCode(String pspCode) {
        this.pspCode = pspCode;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public String getPostbackURL() {
        return postbackURL;
    }

    public void setPostbackURL(String postbackURL) {
        this.postbackURL = postbackURL;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(Double partialAmount) {
        this.partialAmount = partialAmount;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Map<String, String> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, String> extraData) {
        this.extraData = extraData;
    }

    public Long getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Long expiryAt) {
        this.expiryAt = expiryAt;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
