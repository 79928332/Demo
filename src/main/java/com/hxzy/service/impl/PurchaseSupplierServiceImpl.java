package com.hxzy.service.impl;

import com.hxzy.dto.PurchaseSupplierDTO;
import com.hxzy.entity.BaseRepo;
import com.hxzy.entity.PurchaseSupplier;
import com.hxzy.mapper.PurchaseSupplierMapper;
import com.hxzy.service.PurchaseSupplierService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSupplierServiceImpl  extends BaseServiceImpl<PurchaseSupplier,Integer>  implements PurchaseSupplierService {

    private PurchaseSupplierMapper  purchaseSupplierMapper;

    @Autowired
    public void setPurchaseSupplierMapper(PurchaseSupplierMapper purchaseSupplierMapper) {
        this.purchaseSupplierMapper = purchaseSupplierMapper;
        super.setMyBatisBaseDao(purchaseSupplierMapper);
    }

    @Override
    public Result existsValue(PurchaseSupplierDTO purchaseSupplierDTO) {
        if (StringUtils.isNotBlank(purchaseSupplierDTO.getName())) {
            return existsName(purchaseSupplierDTO);
        }
        return existsName(purchaseSupplierDTO);
    }

    private Result existsName(PurchaseSupplierDTO purchaseSupplierDTO) {
        int count = 0;
        //新增
        if (purchaseSupplierDTO.getId()==0) {
            count = this.purchaseSupplierMapper.existsValue(purchaseSupplierDTO);
        } else {
            PurchaseSupplier dbRole = this.purchaseSupplierMapper.selectByPrimaryKey(purchaseSupplierDTO.getId());
            if (dbRole != null) {
                if (!dbRole.getName().equals(purchaseSupplierDTO.getName())) {
                    count = this.purchaseSupplierMapper.existsValue(purchaseSupplierDTO);
                }
            }
        }
        Result result = null;
        //返回给前端  count==0 代表允许使用 ,否则已被占用
        if (count == 0) {
            result = Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        } else {
            result = Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }
        return result;
    }
}
