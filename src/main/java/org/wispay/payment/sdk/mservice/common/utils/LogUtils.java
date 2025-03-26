package org.wispay.payment.sdk.mservice.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LogUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static <T> void log(String message, T object) {
        if (object == null) {
            System.out.println("Object is null");
        } else {
            String json = GSON.toJson(object);
            System.out.println((message != null) ? message : "Logging Process");
            System.out.println(
                    "Logging " + object.getClass().getSimpleName()
                            + " result: \n" + json);
        }
    }
}
