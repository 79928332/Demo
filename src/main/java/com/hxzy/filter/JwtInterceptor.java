package com.hxzy.filter;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.entity.User;
import com.hxzy.util.JwtThreadLocalUtil;
import com.hxzy.util.JwtUtil;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JWT登录验证
 */
public class JwtInterceptor  extends HandlerInterceptorAdapter {

    /**
     * true代表放行 , false拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Result result=null;
        String jwtToken=request.getHeader("X-Token");
        //没有值
        if(StringUtils.isBlank(jwtToken)){
            result=Result.createResult(ResultCode.JWT_ERROR_MALICIOUS);
            printJson(response,result);
            return false;
        }

        //还需要解析
        Claims claims= JwtUtil.parseJwt(jwtToken);
        String jsonStr= claims.get("userInfo",String.class);
        //json串转java
        User user= JSONObject.parseObject(jsonStr, User.class);
        //存放到本地线程池中去,供其它类的方法调用
        JwtThreadLocalUtil.setUser(user);

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //清空jwt本地线程池
        JwtThreadLocalUtil.removeUser();
    }

    /**
     * 向客户端输出json
     * @param resp
     * @param result
     */
    private void printJson(HttpServletResponse resp, Result result){
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            PrintWriter out=resp.getWriter();
            out.print(JSONObject.toJSONString( result));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}