package com.hxzy.entity;

import com.hxzy.util.validate.group.Insert;
import com.hxzy.util.validate.group.Update;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * lz_base_goods
 * @author 
 */
public class BaseGoods implements Serializable {
    private Integer id;

    @NotBlank(message = "商品名称不能为空",groups = {Insert.class, Update.class})
    private String name;
    @NotBlank(message = "商品编码不能为空",groups = {Insert.class, Update.class})
    private String code;
    @NotBlank(message = "商品类型不能为空",groups = {Insert.class})
    private String type;
    @NotBlank(message = "商品品牌不能为空",groups = {Insert.class})
    private String brand;
    @NotBlank(message = "商品计量单位不能为空",groups = {Insert.class})
    private String unit;
    @NotBlank(message = "商品颜色不能为空",groups = {Insert.class})
    private String color;
    @NotBlank(message = "商品规格不能为空",groups = {Insert.class})
    private String standard;
    @NotBlank(message = "商品材质不能为空",groups = {Insert.class})
    private String material;
    @NotBlank(message = "商品进价不能为空",groups = {Insert.class, Update.class})
    private String buyPrice;
    @NotBlank(message = "商品售价不能为空",groups = {Insert.class, Update.class})
    private String salePrice;

    private String descs;

    private String updater;

    private Date updatime;

    private Integer state;

    private String picture;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}