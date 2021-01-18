package com.hxzy.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户查询条件
 */
public class UserSearchDTO extends PageSearch {
    private Integer id;
    private String account;
    private String roleName;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
