package com.hxzy.mapper;

import com.hxzy.dto.BaseParamTypeDTO;
import com.hxzy.entity.BaseParamType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BaseParamTypeMapper继承基类
 */
@Repository
public interface BaseParamTypeMapper extends MyBatisBaseDao<BaseParamType, Integer> {

    List<BaseParamType> typeList();

    int existsValue(BaseParamTypeDTO baseParamTypeDTO);
}