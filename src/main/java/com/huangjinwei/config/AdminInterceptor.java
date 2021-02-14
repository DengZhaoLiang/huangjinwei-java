package com.huangjinwei.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object admin = request.getSession().getAttribute("Admin");
        if (admin != null) {
            System.out.println(admin);
            return true;
        } else {
            response.sendRedirect("/bysj/admin/login");
            return false;
        }
    }

}
