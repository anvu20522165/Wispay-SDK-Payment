package org.wispay.payment.sdk.mservice.model.request.pay;

public class PaymentOptions {

    String sessionId;
    String bankCode;
    boolean showQRCode;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public boolean isShowQRCode() {
        return showQRCode;
    }

    public void setShowQRCode(boolean showQRCode) {
        this.showQRCode = showQRCode;
    }
}