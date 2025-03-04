package org.wispay.payment.sdk.mservice.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.wispay.payment.sdk.mservice.common.exception.WisPayException;

import static org.wispay.payment.sdk.mservice.common.constant.HttpConstant.*;

public class ErrorUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Method to extract error details from a JSON response.
     *
     * @param jsonResponse The JSON response string.
     */
    public static void extractErrorDetails(String jsonResponse) {
        try {
            JsonObject response = GSON.fromJson(jsonResponse, JsonObject.class);

            String traceId = response.has(TRACE_ID) && !response.get(TRACE_ID).isJsonNull()
                    ? response.get(TRACE_ID).getAsString()
                    : null;

            String stringStatus = response.has(STATUS) && !response.get(STATUS).isJsonNull()
                    ? response.get(STATUS).getAsString()
                    : null;
            Integer status = null;
            if (stringStatus != null) {
                status = Integer.parseInt(stringStatus);
            }

            String message = response.has(MESSAGE) && !response.get(MESSAGE).isJsonNull()
                    ? response.get(MESSAGE).getAsString()
                    : null;

            JsonObject data = response.has(DATA) && !response.get(DATA).isJsonNull()
                    ? response.getAsJsonObject(DATA)
                    : null;

            String description = data != null && data.has(DESCRIPTION) && !data.get(DESCRIPTION).isJsonNull()
                    ? data.get(DESCRIPTION).getAsString()
                    : null;

            String debugInfo = data != null && data.has("debugs") && !data.get("debugs").isJsonNull()
                    ? data.getAsJsonObject("debugs").toString()
                    : null;

            throw new WisPayException(traceId, status, message, description, debugInfo);

        } catch (WisPayException e) {
            throw (e);
        } catch (Exception e) {
            throw new RuntimeException("Error while extracting error details", e);
        }
    }

}
