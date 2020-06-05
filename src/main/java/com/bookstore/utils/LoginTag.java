package com.bookstore.utils;

import com.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //获取PageContext对象
        PageContext context = (PageContext) this.getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        //从session获取登录用户信息login_user
        User login_user = (User) context.getSession().getAttribute("login_user");
        if (login_user == null) {//如果用户未登录，则重定向到权限不足的页面
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
        }
    }
}
