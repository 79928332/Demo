package com.hxzy.service;

import com.hxzy.dto.BaseParamTypeDTO;
import com.hxzy.entity.BaseParamType;
import com.hxzy.vo.Result;

public interface  BaseParamTypeService extends BaseService<BaseParamType,Integer>{

    Result typeList();

    Result existsValue(BaseParamTypeDTO baseParamTypeDTO);

}
