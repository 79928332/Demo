package com.hxzy.controller;

import com.hxzy.dto.PurchaseSupplierDTO;
import com.hxzy.entity.PurchaseSupplier;
import com.hxzy.service.PurchaseSupplierService;
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

@RequestMapping(value = "/api/psupplier")
@Controller
public class PurchaseSupplierController {
    @Autowired
    private PurchaseSupplierService purchaseSupplierService;
    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(PurchaseSupplierDTO purchaseSupplierDTO){
        return  this.purchaseSupplierService.search(purchaseSupplierDTO);
    }
    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Validated PurchaseSupplier purchaseSupplier){
        Result result=null;
        purchaseSupplier.setUpdater("admin");
        purchaseSupplier.setUpdatime(new Date());
        purchaseSupplier.setState(1);
        boolean boo=this.purchaseSupplierService.insert(purchaseSupplier);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Validated PurchaseSupplier purchaseSupplier){
        Result result=null;
        purchaseSupplier.setUpdater("admin");
        purchaseSupplier.setUpdatime(new Date());
        purchaseSupplier.setState(1);

        boolean boo=this.purchaseSupplierService.updateByPrimaryKeySelective(purchaseSupplier);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(@Validated PurchaseSupplier purchaseSupplier){
        Result result=null;
        purchaseSupplier.setState(0);
        boolean boo=this.purchaseSupplierService.updateByPrimaryKeySelective(purchaseSupplier);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value="/exist")
    public Result exist(PurchaseSupplierDTO purchaseSupplierDTO){
        return this.purchaseSupplierService.existsValue(purchaseSupplierDTO);
    }
}
