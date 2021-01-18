package com.hxzy.mapper;

import com.hxzy.entity.SaleOrder;
import org.springframework.stereotype.Repository;

/**
 * SaleOrderMapper继承基类
 */
@Repository
public interface SaleOrderMapper extends MyBatisBaseDao<SaleOrder, Integer> {
}