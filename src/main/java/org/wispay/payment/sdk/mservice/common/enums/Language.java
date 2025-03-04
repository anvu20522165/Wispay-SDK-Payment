package org.wispay.payment.sdk.mservice.common.enums;

import com.google.gson.annotations.SerializedName;

import static org.wispay.payment.sdk.mservice.common.constant.Language.LANGUAGE_EN;
import static org.wispay.payment.sdk.mservice.common.constant.Language.LANGUAGE_VI;

public enum Language {

    @SerializedName("vi")
    VI(LANGUAGE_VI),

    @SerializedName("en")
    EN(LANGUAGE_EN);

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public static Language findByName(String name) {
        for (Language type : values()) {
            if (type.getLanguageValue().equals(name)) {
                return type;
            }
        }
        return null;
    }

    public String getLanguageValue() {
        return value;
    }

}
