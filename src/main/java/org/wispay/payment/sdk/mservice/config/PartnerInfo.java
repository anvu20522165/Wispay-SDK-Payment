package org.wispay.payment.sdk.mservice.config;

public class PartnerInfo {

    private String apiKey;
    private String secretKey;

    public PartnerInfo() {
    }

    public PartnerInfo(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
