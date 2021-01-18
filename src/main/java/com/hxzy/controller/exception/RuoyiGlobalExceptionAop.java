package com.hxzy.controller.exception;

import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 当前项目springmvc全局异常统一处理 springaop思想
 */
@RestControllerAdvice
public class RuoyiGlobalExceptionAop {

    //处理hibernate值的认证统一
    @ExceptionHandler(BindException.class)
    public Result hibernateValidatorBindingException(BindException e){
        ObjectError error=e.getAllErrors().get(0);
        return Result.createResult(ResultCode.VALIDATOR_ERROR, error.getDefaultMessage());
    }

    //处理用户登录
    @ExceptionHandler(value = { AccountNotFoundException.class, AccountLockedException.class,InvalidPasswordException.class   })
    public Result userLogin(RuntimeException e){
        return Result.createResult(ResultCode.LOGIN_FALIED, e.getMessage());
    }


    //JWT串被人恶意的更改
    @ExceptionHandler(value = {MalformedJwtException.class   })
    public Result jwtError(RuntimeException e){
        return Result.createResult(ResultCode.JWT_ERROR_MALICIOUS);
    }

    //jwt超时了
    @ExceptionHandler(value = {ExpiredJwtException.class   })
    public Result jwtExpired(RuntimeException e){
        return Result.createResult(ResultCode.JWT_EXPIRED);
    }
}
