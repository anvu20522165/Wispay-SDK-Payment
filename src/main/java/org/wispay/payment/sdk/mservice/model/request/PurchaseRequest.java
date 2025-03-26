package org.wispay.payment.sdk.mservice.model.request;

import org.wispay.payment.sdk.mservice.model.bill.BillParams;

import java.util.List;

public class PurchaseRequest extends Request {
    private BillParams bill;

    private String customer;

    private String Shipping;

    private List<String> items;

    private String callbackURL;

    private String postbackURL;

    public PurchaseRequest() {

    }

    public PurchaseRequest(BillParams bill, String customer, String shipping, List<String> items, String callbackURL, String postbackURL) {
        this.bill = bill;
        this.customer = customer;
        Shipping = shipping;
        this.items = items;
        this.callbackURL = callbackURL;
        this.postbackURL = postbackURL;
    }

    public BillParams getBill() {
        return bill;
    }

    public void setBill(BillParams bill) {
        this.bill = bill;
    }

    public String getShipping() {
        return Shipping;
    }

    public void setShipping(String shipping) {
        Shipping = shipping;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
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
}
