package com.hxzy.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageInfoEntityToEntityVOUtil {

    /**
     * Page<实体类> 转换成 PageInfo<实体类VO>
     * @param cls  转换后的对象VO
     * @param page  数据库查询出来的List转换的Page对象
     * @param list  自定义List<对象VO>
     * @param <Model> 转换后的对象VO泛型
     * @return
     */
    public static  <Model> PageInfo<Model> convertPageInfo(Class cls, Page page, List<Model> list){
        Page<Model> newPage=new Page<Model>(page.getPageNum(),page.getPageSize());
        newPage.setTotal(page.getTotal());
        newPage.setPages(page.getPages());
        newPage.setPageSize(page.getPageSize());
        newPage.setStartRow(page.getStartRow());
        newPage.setEndRow(page.getEndRow());
        PageInfo<Model> pageInfoVO=newPage.toPageInfo();
        pageInfoVO.setList(list);
        return pageInfoVO;
    }
}
