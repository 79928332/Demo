package com.hxzy.service;

import com.hxzy.dto.PurchaseOrderSearchDTO;
import com.hxzy.entity.PurchaseOrder;
import com.hxzy.vo.Result;

public interface  PurchaseOrderService extends BaseService<PurchaseOrder,Integer>{
    Result existsValue(PurchaseOrderSearchDTO purchaseOrderSearchDTO);
}
