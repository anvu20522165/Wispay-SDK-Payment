package org.wispay.payment.sdk.mservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WisPayEndpoint {

    private final String endPoint;
    private String create;
    private String refund;
    private String queryBill;
    private String queryRefund;
    private String paymentMethod;

    public WisPayEndpoint(String endPoint) throws IOException {
        this.endPoint = endPoint;
        initialSettings();
    }

    void initialSettings() throws IOException {
        Properties properties = new Properties();
        InputStream input = Environment.class.getClassLoader().getResourceAsStream("environment.properties");
        properties.load(input);
        create = properties.getProperty("CREATE");
        refund = properties.getProperty("REFUND");
        queryBill = properties.getProperty("QUERY_BILL");
        queryRefund = properties.getProperty("QUERY_REFUND");
        paymentMethod = properties.getProperty("PAYMENT_METHOD");
    }


    public String getEndPoint() {
        return endPoint;
    }

    public String getQueryRefund() {
        return endPoint + queryRefund;
    }

    public String getQueryBill() {
        return endPoint + queryBill;
    }

    public String getRefund() {
        return endPoint + refund;
    }

    public String getCreate() {
        return endPoint + create;
    }

    public String getPaymentMethod() {
        return endPoint + paymentMethod;
    }
}
