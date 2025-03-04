package org.wispay.payment.sdk.mservice.common.exception;

public class WisPayException extends RuntimeException {
    private String traceId;
    private Integer status;
    private String message;
    private String description;
    private String debugInfo;

    public WisPayException(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public WisPayException(String message, Integer status, String debugInfo) {
        this.message = message;
        this.status = status;
        this.debugInfo = debugInfo;
    }

    public WisPayException(String traceId, Integer status, String message, String description, String debugInfo) {
        this.traceId = traceId;
        this.status = status;
        this.message = message;
        this.description = description;
        this.debugInfo = debugInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    @Override
    public String toString() {
        return "ErrorDetails {\n" +
                "traceId = " + traceId + ",\n" +
                "status = " + status + ",\n" +
                "message = '" + message + '\'' + ",\n" +
                "description = " + description + ",\n" +
                "debugInfo = " + debugInfo + '}';
    }

}

