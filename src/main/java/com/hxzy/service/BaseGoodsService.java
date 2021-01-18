package com.hxzy.service;

import com.hxzy.dto.BaseGoodsSearchDTO;
import com.hxzy.entity.BaseGoods;
import com.hxzy.vo.Result;

public interface  BaseGoodsService extends BaseService<BaseGoods,Integer>{

    Result existsValue(BaseGoodsSearchDTO baseGoodsSearchDTO);


}
