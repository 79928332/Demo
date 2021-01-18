package com.hxzy.service;

import com.hxzy.dto.BaseParamDTO;
import com.hxzy.entity.BaseParam;
import com.hxzy.vo.Result;

public interface  BaseParamService extends BaseService<BaseParam,Integer>{

    Result searchAll(BaseParamDTO baseParamDTO);

    Result existsValue(BaseParamDTO baseParamDTO);
}
