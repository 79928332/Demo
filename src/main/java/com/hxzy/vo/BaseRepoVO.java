package com.hxzy.vo;
import com.hxzy.entity.BaseRepo;
public class BaseRepoVO extends BaseRepo {
    private String mobile;
    private String empName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
