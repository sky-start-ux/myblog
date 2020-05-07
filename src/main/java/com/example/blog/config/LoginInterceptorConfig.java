package com.example.blog.config;

import com.example.blog.utils.CookieUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptorConfig extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (CookieUtils.getCookies(request).get("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
