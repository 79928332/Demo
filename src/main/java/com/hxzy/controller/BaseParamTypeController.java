package com.hxzy.controller;

import com.hxzy.dto.BaseParamTypeDTO;
import com.hxzy.entity.BaseParamType;
import com.hxzy.service.BaseParamTypeService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/api/baseparamtype")
public class BaseParamTypeController {

    @Autowired
    private BaseParamTypeService baseParamTypeService;

    @ResponseBody
    @GetMapping(value = "/typelist")
    public Result typeList(){
        return this.baseParamTypeService.typeList();

    }
    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(BaseParamTypeDTO baseParamTypeDTO){
        return this.baseParamTypeService.search(baseParamTypeDTO);

    }
    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Validated BaseParamType baseParamType){

        Result result=null;
        baseParamType.setId(0);
        baseParamType.setState(1);

        boolean boo=this.baseParamTypeService.insert(baseParamType);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Validated BaseParamType baseParamType) {

        Result result = null;

        boolean boo = this.baseParamTypeService.updateByPrimaryKeySelective(baseParamType);
        if (boo) {
            result = Result.createResult(ResultCode.DB_SAVE_OK);
        } else {
            result = Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/exist")
    public Result exist(BaseParamTypeDTO baseParamTypeDTO){
        return this.baseParamTypeService.existsValue(baseParamTypeDTO);
    }
}
