package com.hxzy.controller;

import com.hxzy.dto.UserLoginDTO;
import com.hxzy.service.UserService;
import com.hxzy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * 登录 (使用springmvc 全局统一异常来解决)
     * @param userLoginDTO
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@Valid UserLoginDTO userLoginDTO, HttpServletRequest request){


        return this.userService.login(userLoginDTO);
    }


    /**
     * 根据jwt获取登录的信息
     * @return
     */
    @GetMapping(value = "/info")
    public Result getUserInfoByJwt( @RequestHeader(value = "X-Token") String jwtToken){
        return  this.userService.getUserInfoByJwt(jwtToken);
    }

}
