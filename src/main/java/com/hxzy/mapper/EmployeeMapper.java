package com.hxzy.mapper;

import com.hxzy.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * EmployeeMapper继承基类
 */
@Repository
public interface EmployeeMapper extends MyBatisBaseDao<Employee, Integer> {
}