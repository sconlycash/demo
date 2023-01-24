package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检测session中是否有uid数据,如果有则执行，没有则重定向
 * request 请求对象
 * response 响应对象
 * handler 处理器 url controller 映射
 * 如果返回值为true表示放行当前的请求如果为false则表示拦截
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        if(obj==null){
            //说明用户没有登陆过系统,则重定向
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;

    }
}
