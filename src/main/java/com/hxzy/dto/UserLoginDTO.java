package com.hxzy.dto;

import com.hxzy.util.RegExpValidateUtil;
import com.hxzy.util.UserLoginEnum;

import javax.validation.constraints.NotBlank;

/**
 * 登录设计入参数类
 */
public class UserLoginDTO {

    /**
     * 记录登录IP
     */
    private String loginIp;
    /**
     * 它可以有邮件、电话、用户名
     */
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
        this.getLoginType();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String loginTypeName;
    /**
     * 登录方式字符串
     * @return
     */
    public String getLoginTypeName(){
        return  this.loginTypeName;
    }


    /**
     * 取得登录的方式(枚举)
     * @return
     */
    public UserLoginEnum getLoginType(){
        //判断它的格式
        UserLoginEnum  loginEnum=UserLoginEnum.USERNAME;

        if(RegExpValidateUtil.isEmail(this.loginName)){
            loginEnum=UserLoginEnum.EMAIL;
        }else if(RegExpValidateUtil.isMobile(this.loginName)){
            loginEnum=UserLoginEnum.MOBILE;
        }

        this.loginTypeName=loginEnum.getValue();

        return loginEnum;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}

