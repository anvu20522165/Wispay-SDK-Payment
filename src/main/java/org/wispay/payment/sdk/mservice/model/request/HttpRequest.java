package org.wispay.payment.sdk.mservice.model.request;

import org.wispay.payment.sdk.mservice.common.enums.Language;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String url;

    private String payload;

    private String contentType;

    private Map<String, Object> headers;

    private Language language;

    private int connectionTimeout = Integer.MAX_VALUE;

    private int writeTimeout = Integer.MAX_VALUE;

    private int readTimeout = Integer.MAX_VALUE;

    public HttpRequest(String method, String url, String payload, String contentType) {
        this.method = method;
        this.url = url;
        this.payload = payload;
        this.contentType = contentType;
    }

    public HttpRequest(String method, String url, String payload, String contentType, Map<String, Object> headers, Language language) {
        this.method = method;
        this.url = url;
        this.payload = payload;
        this.contentType = contentType;
        this.headers = headers;
        this.language = language;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
