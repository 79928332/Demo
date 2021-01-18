package com.hxzy.vo;

import com.hxzy.entity.Menu;

import java.util.List;

/**
 * 返回结果
 */
public class MenuTreeTableVO extends Menu {

    private List<MenuTreeTableVO>   children;

    public List<MenuTreeTableVO> getChildren() {
        return children;
    }

    /**
     * 设定子节点
     * @param children
     */
    public void setChildren(List<MenuTreeTableVO> children) {
        this.children = children;
    }
}
