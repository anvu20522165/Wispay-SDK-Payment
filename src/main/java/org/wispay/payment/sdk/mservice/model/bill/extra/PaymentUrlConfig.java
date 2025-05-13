package org.wispay.payment.sdk.mservice.model.bill.extra;

public class PaymentUrlConfig {
    //Tuỳ chọn phương thức thanh toán
    private String[] pspCodes;

    //ON - OFF cancel bill
    private Boolean isCancelable = true;

    //Thanh toán trực tiếp
    //Direct về trang WisPay hoặc trang Psp
    //Kết hợp với 1 pspCode chỉ định
    private Boolean isDirect = false;

    //Cho phép sử dụng link thanh toán nhiều lần
    private Boolean isReusable = false;

    //Mã mong muốn tạo short link
    private String shortLinkCode;

    //Tên của đường dẫn thanh toán
    private String name;

    public String[] getPspCodes() {
        return pspCodes;
    }

    public void setPspCodes(String[] pspCodes) {
        this.pspCodes = pspCodes;
    }

    public Boolean getCancelable() {
        return isCancelable;
    }

    public void setCancelable(Boolean cancelable) {
        isCancelable = cancelable;
    }

    public Boolean getDirect() {
        return isDirect;
    }

    public void setDirect(Boolean direct) {
        isDirect = direct;
    }

    public Boolean getReusable() {
        return isReusable;
    }

    public void setReusable(Boolean reusable) {
        isReusable = reusable;
    }

    public String getShortLinkCode() {
        return shortLinkCode;
    }

    public void setShortLinkCode(String shortLinkCode) {
        this.shortLinkCode = shortLinkCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
