package com.bookstore.client.user.handler;

import com.bookstore.client.user.service.IUserService;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/4/14
 */
@Controller
@RequestMapping("/client/user")
public class UserHandler {
    @Autowired
    IUserService userService;
    //
    // //查看邮箱是否被注册
    // @RequestMapping("/findEmail.do")
    // @ResponseBody
    // public String findEmail(String email){
    //     User user = userService.findEmail(email);
    //     if (user != null){
    //         return "EXIST";
    //     }else{
    //         return "OK";
    //     }
    // }

    @RequestMapping("/register")
    public String register(User user, String checkCode, HttpServletRequest request, HttpSession session){
        // System.out.println("信息"+user);
        // System.out.println("校验码"+checkCode);

        String checkcode_session =(String) session.getAttribute("checkcode_session");
        if (checkcode_session.equals(checkCode)){
            int rows = userService.addUser(user);
            if(rows>0){
                return "redirect:/client/registersuccess.jsp";
            }else {
                request.setAttribute("fail","新用户注册失败，请重试");
                return "/client/register.jsp";
            }

        }else {//校验码不正确
            request.setAttribute("check_error","校验码错误，请重新输入");
            return "/client/register.jsp";
        }
    }
}
