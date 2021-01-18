package com.hxzy.mapper;

import com.hxzy.entity.BaseEmployee;
import org.springframework.stereotype.Repository;

/**
 * BaseEmployeeMapper继承基类
 */
@Repository
public interface BaseEmployeeMapper extends MyBatisBaseDao<BaseEmployee, Integer> {
}