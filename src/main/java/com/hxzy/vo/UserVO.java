package com.hxzy.vo;

import com.hxzy.entity.User;

/**
 * 用户查询返回结果
 */
public class UserVO extends User {
    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
