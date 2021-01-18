package com.hxzy.controller;

import com.hxzy.dto.SaleOrderSearchDTO;
import com.hxzy.entity.SaleOrder;
import com.hxzy.service.SaleOrderService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/api/saleorder")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService ;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(SaleOrderSearchDTO saleOrderSearchDTO){
        return this.saleOrderService.search(saleOrderSearchDTO);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(SaleOrder saleOrder){
        Result result=null;
        saleOrder.setUpdatime(new Date());
        boolean boo=this.saleOrderService.updateByPrimaryKeySelective(saleOrder);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }
}
