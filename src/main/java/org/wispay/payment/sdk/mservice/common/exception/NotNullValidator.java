package org.wispay.payment.sdk.mservice.common.exception;

import org.wispay.payment.sdk.mservice.common.annotation.NotNull;

import java.lang.reflect.Field;
import java.util.Objects;

public class NotNullValidator {
    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (Objects.isNull(value)) {
                    String fieldName = field.getName();
                    throw new IllegalArgumentException("Field '" + fieldName + "' cannot be null");
                }
            }
        }
    }
}

