package com.hxzy.mapper;

import com.hxzy.dto.BaseParamDTO;
import com.hxzy.entity.BaseParam;
import com.hxzy.vo.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BaseParamMapper继承基类
 */
@Repository
public interface BaseParamMapper extends MyBatisBaseDao<BaseParam, Integer> {

    List<BaseParam> searchAll(BaseParamDTO baseParamDTO);

    int existsValue(BaseParamDTO baseParamDTO);
}