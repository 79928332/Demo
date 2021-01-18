package com.hxzy.vo;

import com.hxzy.entity.SaleOrder;

public class SaleOrderVO extends SaleOrder{

    private String goodsName;
    private String repoName;
    private String unitName;
    //审核状态中文显示
    private String checkStates;
    //订单类型中文显示
    private String typeName;

    //显示商品类别
    private String goodsType;

    //显示品牌
    private String brand;

    //显示员工
    private String employeeName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getCheckStates() {
        return checkStates;
    }

    public void setCheckStates(String checkStates) {
        this.checkStates = checkStates;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
