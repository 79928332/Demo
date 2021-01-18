package com.hxzy.util;

/**
 * 用户登录枚举
 */
public enum UserLoginEnum {
    /**
     * 邮箱登录
     */
    EMAIL(1,"email"),
    /**
     * 手机号码登录
     */
    MOBILE(2,"mobile"),
    /**
     * 用户名登录
     */
    USERNAME(3,"userName");

    private String value;
    private int key;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    UserLoginEnum(int key, String value) {
        this.value = value;
        this.key = key;
    }
}

