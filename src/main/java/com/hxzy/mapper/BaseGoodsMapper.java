package com.hxzy.mapper;

import com.hxzy.dto.BaseGoodsSearchDTO;
import com.hxzy.entity.BaseGoods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BaseGoodsMapper继承基类
 */
@Repository
public interface BaseGoodsMapper extends MyBatisBaseDao<BaseGoods, Integer> {

    int existsValue(BaseGoodsSearchDTO baseGoodsSearchDTO);

}