package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.dto.PageSearch;
import com.hxzy.mapper.MyBatisBaseDao;
import com.hxzy.service.BaseService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 父类最好不要被别人实例化
 * @param <Model>
 * @param <PK>
 */
@Transactional
public abstract class BaseServiceImpl<Model, PK extends Serializable> implements BaseService<Model,PK> {
    //日志
    protected Logger logger=Logger.getLogger(this.getClass());

    private MyBatisBaseDao  myBatisBaseDao;

    /**
     * 留出方法给别人赋值
     * @param myBatisBaseDao
     */
    public void setMyBatisBaseDao(MyBatisBaseDao myBatisBaseDao) {
        this.myBatisBaseDao = myBatisBaseDao;
    }

    @Override
    public boolean deleteByPrimaryKey(PK id) {
        return this.myBatisBaseDao.deleteByPrimaryKey(id)>0;
    }


    @Override
    public boolean insert(Model record) {
        return this.myBatisBaseDao.insert(record)>0;
    }

    @Override
    public boolean insertSelective(Model record) {
        return this.myBatisBaseDao.insertSelective(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Model selectByPrimaryKey(PK id) {
        return (Model) this.myBatisBaseDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Model record) {
        return this.myBatisBaseDao.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Model record) {
        return this.myBatisBaseDao.updateByPrimaryKey(record)>0;
    }

    @Override
    public Result search(PageSearch pageSearch) {
        //第一步开启分页模式
        PageHelper.startPage(pageSearch.getPage(), pageSearch.getSize());

        //查询
        List<Model>  arr=this.myBatisBaseDao.search(pageSearch);

        //转换成分页地象
        Page pg=(Page) arr;

        PageInfo  pageInfo= pg.toPageInfo();

        //结果按规范返回
        Result  result=Result.createResult_Data(ResultCode.OK, pageInfo);

        return result;

    }


}
