package org.wispay.payment.sdk.mservice.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.wispay.payment.sdk.mservice.model.request.HttpRequest;
import org.wispay.payment.sdk.mservice.model.response.HttpResponse;

import java.io.IOException;
import java.util.Map;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;

public class WisPayExecutor {

    private final CloseableHttpClient client = HttpClients.createDefault();

    private static void setEntity(HttpEntityEnclosingRequestBase request, String payload) {
        if (payload != null) {
            request.setEntity(new StringEntity(payload, "UTF-8"));
        }
    }

    public HttpResponse sendToWisPay(HttpRequest httpRequest) {
        LogUtils.log("HTTP request: ", httpRequest);
        HttpRequestBase request = createHttpRequest(httpRequest);

        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            HttpResponse httpResponse = new HttpResponse(response.getStatusLine().getStatusCode(), responseBody, response.getAllHeaders());
            return httpResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequestBase createHttpRequest(HttpRequest httpRequest) {
        HttpRequestBase request;

        String url = httpRequest.getUrl() + "?language=" + httpRequest.getLanguage().getLanguageValue();
        httpRequest.setUrl(url);

        switch (httpRequest.getMethod().toUpperCase()) {
            case GET:
                request = new HttpGet(httpRequest.getUrl());
                break;
            case POST:
                request = new HttpPost(httpRequest.getUrl());
                setEntity((HttpEntityEnclosingRequestBase) request, httpRequest.getPayload());
                break;
            case PUT:
                request = new HttpPut(httpRequest.getUrl());
                setEntity((HttpEntityEnclosingRequestBase) request, httpRequest.getPayload());
                break;
            case DELETE:
                request = new HttpDelete(httpRequest.getUrl());
                break;
            default:
                throw new UnsupportedOperationException("HTTP method not supported: " + httpRequest.getMethod());
        }

        if (httpRequest.getHeaders() != null) {
            for (Map.Entry<String, Object> header : httpRequest.getHeaders().entrySet()) {
                request.addHeader(header.getKey(), String.valueOf(header.getValue()));
            }
        }

        return request;
    }
}
