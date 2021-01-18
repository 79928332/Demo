package com.hxzy.service;

import com.hxzy.dto.PageSearch;
import com.hxzy.vo.Result;

import java.io.Serializable;

/**
 *业务逻辑公共基类
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK> The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 */
public interface BaseService<Model, PK extends Serializable> {
    boolean deleteByPrimaryKey(PK id);

    boolean insert(Model record);

    boolean insertSelective(Model record);

    Model selectByPrimaryKey(PK id);

    boolean updateByPrimaryKeySelective(Model record);

    boolean updateByPrimaryKey(Model record);

    /**
     * 分页
     * @param pageSearch
     * @return
     */
    Result search(PageSearch pageSearch);


}