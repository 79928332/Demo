package com.hxzy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FilterCors implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  req=(HttpServletRequest) request;
        HttpServletResponse  resp=(HttpServletResponse) response;

        resp.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));

        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        //是否允许浏览器携带用户身份信息（cookie）
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
