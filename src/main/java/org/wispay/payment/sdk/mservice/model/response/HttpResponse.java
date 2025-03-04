package org.wispay.payment.sdk.mservice.model.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.Header;

public class HttpResponse {
    private int statusCode;
    private String payload;
    private String contentType;
    private boolean isSent;
    private Header[] headers;

    public HttpResponse(int statusCode, String payload, Header[] headers) {
        this.statusCode = statusCode;
        this.payload = payload;
        this.headers = headers;
    }

    public JsonElement getData() {
        if (this.payload == null || this.payload.isEmpty()) {
            throw new IllegalArgumentException("Payload is empty!");
        }

        JsonObject rootObject = JsonParser.parseString(this.payload).getAsJsonObject();
        JsonElement dataElement = rootObject.get("data");

        if (dataElement.isJsonArray()) {
            return dataElement.getAsJsonArray();
        } else if (dataElement.isJsonObject()) {
            return dataElement.getAsJsonObject();
        } else {
            throw new IllegalArgumentException("Data is neither a JsonObject nor a JsonArray");
        }
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}
