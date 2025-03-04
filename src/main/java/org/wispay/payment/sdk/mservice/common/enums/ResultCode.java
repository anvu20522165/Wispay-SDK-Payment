package org.wispay.payment.sdk.mservice.common.enums;

public enum ResultCode {
    //Return Code of payment with ZALO PAY
    SUCCESS(0, "Giao dịch thành công"),
    FAIL(2, "Giao dịch thất bại"),
    INVALID_DATA(8000, "Dữ liệu không hợp lệ");

    private final Integer code;
    private final String msgCode;

    ResultCode(Integer code, String msgCode) {
        this.code = code;
        this.msgCode = msgCode;
    }

    public static ResultCode findByCode(Integer resultCode) {
        for (ResultCode x : values()) {
            if (x.getCode().equals(resultCode)) {
                return x;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsgCode() {
        return msgCode;
    }

}
