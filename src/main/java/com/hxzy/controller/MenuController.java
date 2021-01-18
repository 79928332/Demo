package com.hxzy.controller;

import com.hxzy.dto.MenuSearchDTO;
import com.hxzy.entity.Menu;
import com.hxzy.service.MenuService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

@RequestMapping(value = "/api/menu")
@Controller
public class MenuController {


    @Autowired
    private MenuService  menuService;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(MenuSearchDTO  menuSearchDTO){
        return this.menuService.search(menuSearchDTO);
    }
    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Valid Menu menu){
        Result result=null;
        menu.setUpdatime(new Date());
        menu.setIsLeaf(1);
        menu.setState(1);
        menu.setUpdater("admin");
        boolean isSuccessed=this.menuService.insert(menu);
        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @GetMapping(value = "/exist")
    public Result existsValue(MenuSearchDTO menuSearchDTO){
        return this.menuService.existsValue(menuSearchDTO);
    }
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Valid Menu menu){
        Result  result=null;
        boolean isSuccessed=this.menuService.updateByPrimaryKeySelective(menu);

        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }
    @ResponseBody
    @PostMapping(value = "/delete")
    public Result delete(@Valid Menu menu){
        Result  result=null;
        menu.setState(0);
        boolean isSuccessed=this.menuService.updateByPrimaryKeySelective(menu);

        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }
}
