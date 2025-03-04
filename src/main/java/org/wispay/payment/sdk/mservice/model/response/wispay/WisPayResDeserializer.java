package org.wispay.payment.sdk.mservice.model.response.wispay;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.MESSAGE;
import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.STATUS;

public class WisPayResDeserializer<T> implements JsonDeserializer<WisPayResponse<T>> {
    public static final String DATA = "data";

    private final Class<T> clazz;

    public WisPayResDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public WisPayResponse<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement dataElement = jsonObject.get(DATA);
        T data;

        if (dataElement != null && !dataElement.isJsonNull()) {
            if (dataElement.isJsonObject()) {
                data = context.deserialize(dataElement, ((ParameterizedType) type).getActualTypeArguments()[0]);
            } else {
                data = (T) dataElement.getAsString();
            }
        } else {
            data = null;
        }

        return new WisPayResponse<>(
                data,
                jsonObject.get(MESSAGE).getAsString(),
                jsonObject.get(STATUS).getAsInt()
        );
    }

}


