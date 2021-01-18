package com.hxzy.mapper;

import com.hxzy.dto.PurchaseSupplierDTO;
import com.hxzy.entity.PurchaseSupplier;
import org.springframework.stereotype.Repository;

/**
 * PurchaseSupplierMapper继承基类
 */
@Repository
public interface PurchaseSupplierMapper extends MyBatisBaseDao<PurchaseSupplier, Integer> {
    int existsValue(PurchaseSupplierDTO purchaseSupplierDTO);
}