package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.hxzy.util.validate.group.Insert;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * lz_sys_role
 * @author 
 */
public class Role implements Serializable {
    private Integer id;
    @NotEmpty(message = "角色名称不能为空",groups = {Insert.class})
    private String name;
    @NotEmpty(message = "角色编码不能为空",groups = {Insert.class})
    private String code;

    private String descs;

    private String menuIds;

    private String menuNames;

    private Integer state;

    private String updater;

    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date updatime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public String getMenuNames() {
        return menuNames;
    }

    public void setMenuNames(String menuNames) {
        this.menuNames = menuNames;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}