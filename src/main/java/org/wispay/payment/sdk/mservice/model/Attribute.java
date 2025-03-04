package org.wispay.payment.sdk.mservice.model;

public class Attribute {
    private String key;
    private String value;
    private String name;

    public Attribute(String key, String value, String name) {
        this.key = key;
        this.value = value;
        this.name = name;
    }

    public Attribute() {
        super();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}