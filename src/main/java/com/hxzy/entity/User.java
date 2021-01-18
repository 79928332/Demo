package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.hxzy.util.validate.group.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * lz_sys_user
 * @author 
 */
public class User implements Serializable {
    private Integer id;
    @NotEmpty(message = "用户账号不能为空",groups = {Insert.class})
    private String account;
    @NotEmpty(message = "密码不能空", groups = {Insert.class})
    //不要序列化到json串中
    @JSONField(serialize = false)
    private String password;

    private Integer roleId;
    @NotEmpty(message = "用户角色不能为空",groups = {Insert.class})
    private String roleName;

    private Integer employeeId;

    private String updater;
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date updatime;

    private Integer state;

    private Integer isLocked;

    private Date lockTime;

    private static final long serialVersionUID = 1L;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdatime() {
        return updatime;
    }

    public void setUpdatime(Date updatime) {
        this.updatime = updatime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
}