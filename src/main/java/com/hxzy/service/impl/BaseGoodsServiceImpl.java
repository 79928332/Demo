package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.dto.BaseGoodsSearchDTO;
import com.hxzy.dto.PageSearch;
import com.hxzy.entity.BaseGoods;
import com.hxzy.mapper.BaseGoodsMapper;
import com.hxzy.service.BaseGoodsService;
import com.hxzy.util.RuoYiConst;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BaseGoodsServiceImpl  extends BaseServiceImpl<BaseGoods,Integer>  implements BaseGoodsService {

    private BaseGoodsMapper  baseGoodsMapper;

    @Autowired
    public void setBaseGoodsMapper(BaseGoodsMapper baseGoodsMapper) {
        this.baseGoodsMapper = baseGoodsMapper;
        super.setMyBatisBaseDao(baseGoodsMapper);
    }


    @Override
    public Result existsValue(BaseGoodsSearchDTO baseGoodsSearchDTO) {
        int count=0;
        System.out.println(baseGoodsSearchDTO.getId());
        if(baseGoodsSearchDTO.getId()==0){
            count=this.baseGoodsMapper.existsValue(baseGoodsSearchDTO);
        }else {
            System.out.println(baseGoodsSearchDTO.getName());
            BaseGoods dbbase=this.baseGoodsMapper.selectByPrimaryKey(baseGoodsSearchDTO.getId());
            if(dbbase!=null){
               if(StringUtils.isNotBlank(baseGoodsSearchDTO.getName())){
                   if(!dbbase.getName().equals(baseGoodsSearchDTO.getName())){
                       count=this.baseGoodsMapper.existsValue(baseGoodsSearchDTO);
                   }
               }else if(StringUtils.isNotBlank(baseGoodsSearchDTO.getCode())){
                   if(!dbbase.getCode().equals(baseGoodsSearchDTO.getCode())){
                       count=this.baseGoodsMapper.existsValue(baseGoodsSearchDTO);
                   }
               }else if(StringUtils.isNotBlank(dbbase.getType())){
                   if(!dbbase.getType().equals(baseGoodsSearchDTO.getType())){
                       count=this.baseGoodsMapper.existsValue(baseGoodsSearchDTO);
                   }
                }
            }
        }
        Result result=null;
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else {
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }
        return result;
    }


}
