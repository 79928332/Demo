package com.hxzy.mapper;

import com.hxzy.dto.PurchaseOrderSearchDTO;
import com.hxzy.entity.PurchaseOrder;
import org.springframework.stereotype.Repository;

/**
 * PurchaseOrderMapper继承基类
 */
@Repository
public interface PurchaseOrderMapper extends MyBatisBaseDao<PurchaseOrder, Integer>  {
    int existsValue(PurchaseOrderSearchDTO purchaseOrderSearchDTO);
}