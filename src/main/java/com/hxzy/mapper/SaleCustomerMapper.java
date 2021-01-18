package com.hxzy.mapper;

import com.hxzy.entity.SaleCustomer;
import org.springframework.stereotype.Repository;

/**
 * SaleCustomerMapper继承基类
 */
@Repository
public interface SaleCustomerMapper extends MyBatisBaseDao<SaleCustomer, Integer> {
}