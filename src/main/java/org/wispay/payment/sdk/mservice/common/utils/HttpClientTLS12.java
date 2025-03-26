//package org.wispay.payment.sdk.mservice.common.utils;
//
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustAllStrategy;
//import org.apache.http.ssl.SSLContextBuilder;
//import javax.net.ssl.SSLContext;
//
//public class HttpClientTLS12 {
//    private final CloseableHttpClient client;
//
//    public HttpClientTLS12() {
//        try {
//            SSLContext sslContext = SSLContextBuilder.create()
//                    .setProtocol("TLSv1.2")
//                    .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
//                    .build();
//
//            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
//                    sslContext,
//                    new String[]{"TLSv1.2"},
//                    null,
//                    NoopHostnameVerifier.INSTANCE
//            );
//
//            client = HttpClients.custom()
//                    .setSSLSocketFactory(socketFactory)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to initialize HttpClientTLS12", e);
//        }
//    }
//
//
//    public CloseableHttpClient getClient() {
//        return client;
//    }
//}
