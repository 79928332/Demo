package com.hxzy.controller;

import com.hxzy.dto.BaseGoodsSearchDTO;
import com.hxzy.entity.BaseGoods;
import com.hxzy.service.BaseGoodsService;
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

@Controller
@RequestMapping(value = "/api/basegoods")
public class  BaseGoodsController {

    @Autowired
    private BaseGoodsService baseGoodsService;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(BaseGoodsSearchDTO baseGoodsSearchDTO){
        return this.baseGoodsService.search(baseGoodsSearchDTO);
    }

    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Validated BaseGoods baseGoods){
        Result result=null;


        baseGoods.setUpdater("admin");
        baseGoods.setUpdatime(new Date());
        baseGoods.setState(1);

        boolean boo=this.baseGoodsService.insert(baseGoods);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Validated BaseGoods baseGoods){
        Result result=null;
        baseGoods.setUpdater("admin");
        baseGoods.setUpdatime(new Date());
        baseGoods.setState(1);

        boolean boo=this.baseGoodsService.updateByPrimaryKeySelective(baseGoods);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(@Validated BaseGoods baseGoods){
        Result result=null;
        baseGoods.setState(0);

        boolean boo=this.baseGoodsService.updateByPrimaryKeySelective(baseGoods);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value="/exist")
    public Result exist(BaseGoodsSearchDTO baseGoodsSearchDTO){
        return this.baseGoodsService.existsValue(baseGoodsSearchDTO);
    }

}
