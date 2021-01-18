package com.hxzy.vo;

public enum ResultCode {

    /**
     * 操作成功
     */
    OK(0,"操作成功"),
    /**
     * 操作失败
     */
    ERROR(500,"操作失败"),

    /**
     * 参数有误
     */
    VALIDATOR_ERROR(400,"参数有误"),

    /**
     * 参数有误
     */
    LOGIN_FALIED(501,"用户名或密码错误"),

    /**
     * 数据库操作成功
     */
    DB_SAVE_OK(0,"数据库操作成功"),

    /**
     * 数据库操作失败
     */
    DB_SAVE_ERROR(500,"数据库操作失败"),

    VALUE_IS_ALREADY_USE(0,"该值已被占用"),
    VALUE_IS_CAN_BE_USE(0,"OK"),
    JWT_ERROR_MALICIOUS(50008,"令牌有误，请重新登录!"),
    JWT_EXPIRED(50014,"令牌已过期!");





    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }




}
