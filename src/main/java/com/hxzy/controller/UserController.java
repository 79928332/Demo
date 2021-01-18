package com.hxzy.controller;

import com.hxzy.dto.UserSearchDTO;
import com.hxzy.entity.Role;
import com.hxzy.entity.User;
import com.hxzy.service.RoleService;
import com.hxzy.service.UserService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService  userService;
    @Autowired
    private RoleService roleService;
    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(UserSearchDTO userSearchDTO){
        return this.userService.search(userSearchDTO);
    }

    @ResponseBody
    @GetMapping(value = "/exist")
    public Result exist(UserSearchDTO userSearchDTO){
        return this.userService.existsValue(userSearchDTO);
    }

    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Valid User user){
        Result result=null;

        user.setPassword("1234");
        user.setUpdater("admin");
        user.setState(1);
        user.setIsLocked(0);
        user.setUpdatime(new Date());
        Role role=this.roleService.selectByPrimaryKey(user.getRoleId());
        user.setRoleName(role.getName());
        boolean boo = this.userService.insert(user);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    //修改
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Valid User user){
        Result  result=null;
        boolean isSuccessed=this.userService.updateByPrimaryKeySelective(user);
        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    public Result delete(@Valid User user){
        Result  result=null;
        user.setState(0);
        user.setPassword("123");
        user.setLockTime(new Date());
        user.setIsLocked(1);
        boolean isSuccessed=this.userService.updateByPrimaryKeySelective(user);

        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }
}
