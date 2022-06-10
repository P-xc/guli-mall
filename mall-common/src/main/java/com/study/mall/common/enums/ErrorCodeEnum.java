package com.study.mall.common.enums;

/**
 * @author Harlan
 * @date 2021 10 17 1:28
 */
public enum ErrorCodeEnum {

    UNKNOWN_EXCEPTION(10000, "系统未知异常"),

    VALID_EXCEPTION(10001, "参数校验失败"),

    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高"),

    PRODUCT_UP_EXCEPTION(11000, "商品上架异常");

    private final int code;

    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
