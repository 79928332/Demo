package com.hxzy.service;


import com.hxzy.dto.PurchaseSupplierDTO;
import com.hxzy.entity.PurchaseSupplier;
import com.hxzy.vo.Result;

public interface  PurchaseSupplierService extends BaseService<PurchaseSupplier,Integer>{
    Result existsValue(PurchaseSupplierDTO purchaseSupplierDTO);
}
