package com.hxzy.dto;

/**
 * 分页查询基类(父类)
 */
public class PageSearch {
    //当前第几页
    private int page=1;
    //每页几笔
    private int size=5;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageSearch{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }


}

