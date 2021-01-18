package com.hxzy.controller;

import com.hxzy.dto.BaseParamDTO;
import com.hxzy.entity.BaseParam;
import com.hxzy.service.BaseParamService;
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
@RequestMapping(value = "/api/baseparam")
public class BaseParamController {

    @Autowired
    private BaseParamService baseParamService;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(BaseParamDTO baseParamDTO){
        return this.baseParamService.search(baseParamDTO);
    }

    @ResponseBody
    @GetMapping(value = "/searchAll")
    public Result searchAll(BaseParamDTO baseParamDTO){
        return this.baseParamService.searchAll(baseParamDTO);
    }
    @ResponseBody
    @GetMapping(value="/exist")
    public Result exist(BaseParamDTO baseParamDTO){
        return this.baseParamService.existsValue(baseParamDTO);
    }

    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Validated BaseParam baseParam){
        Result result=null;

        baseParam.setId(0);
        baseParam.setCreater("admin");
        baseParam.setCreatime(new Date());
        baseParam.setState(1);

        boolean boo=this.baseParamService.insert(baseParam);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Validated BaseParam baseParam){
        Result result=null;

        baseParam.setCreater("admin");
        baseParam.setCreatime(new Date());
        baseParam.setState(1);

        boolean boo=this.baseParamService.updateByPrimaryKeySelective(baseParam);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(@Validated BaseParam baseParam){
        Result result=null;
        baseParam.setState(0);

        boolean boo=this.baseParamService.updateByPrimaryKeySelective(baseParam);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
}
