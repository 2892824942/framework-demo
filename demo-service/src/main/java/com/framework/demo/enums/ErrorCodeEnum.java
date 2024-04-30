package com.framework.demo.enums;

import com.ty.mid.framework.common.constant.BaseCode;

public enum ErrorCodeEnum implements BaseCode {
    LOGIN_FAIL("10001", "用户名或密码错误"),
    ;

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
