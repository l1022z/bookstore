package com.bookstore.client.user.handler;

import com.bookstore.commons.beans.User;
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
    // @Autowired
    // private IUserService userService;
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

    @RequestMapping("/register.do")
    public String register(User user, String checkCode, HttpServletRequest request, HttpSession session){
        String checkcode_session =(String) session.getAttribute("checkcode_session");
        if (checkcode_session.equals(checkCode)){

        }
        return null;
    }
}
