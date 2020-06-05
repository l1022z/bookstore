package com.bookstore.admin.Interceptor;

import com.bookstore.commons.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行拦截器方法！");
        String url = request.getRequestURI();
        if (url.endsWith("/login")||url.endsWith("/logout")) {
            return true;
        }
        User login_user = (User) request.getSession().getAttribute("login_user");
        if (login_user != null) {
            if ("超级管理员".equals(login_user.getRole())) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
                return false;
            }
        } else {
            response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
            return false;
        }

    }
}
