package com.hxzy.controller;

import com.hxzy.dto.PurchaseOrderSearchDTO;
import com.hxzy.entity.PurchaseOrder;
import com.hxzy.service.PurchaseOrderService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@RequestMapping(value = "/api/purchaseOrder")
@Controller
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(PurchaseOrderSearchDTO purchaseOrderSearchDTO) {
        return this.purchaseOrderService.search(purchaseOrderSearchDTO);
    }
    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Validated PurchaseOrder purchaseOrder) {
        Result result = null;
        purchaseOrder.setUpdater("admin");
       purchaseOrder.setUpdatime(new Date());
       purchaseOrder.setState(1);
        boolean boo = this.purchaseOrderService.insert(purchaseOrder);
        if (boo) {
            result = Result.createResult(ResultCode.DB_SAVE_OK);
        } else {
            result = Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(@Validated PurchaseOrder purchaseOrder){
        Result result=null;
        purchaseOrder.setState(0);
        boolean boo=this.purchaseOrderService.updateByPrimaryKeySelective(purchaseOrder);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
}