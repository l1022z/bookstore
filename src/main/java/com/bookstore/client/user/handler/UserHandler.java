package com.bookstore.client.user.handler;

import com.bookstore.client.user.service.IUserService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client/user")
public class UserHandler {

    @Autowired
    IUserService userService;

    @RequestMapping("/register")
    public String register(User user, String checkCode, HttpSession session, HttpServletRequest request) {
        //System.out.println("注册用户信息："+user);
        //System.out.println("校验码输入："+checkCode);
        user.setActiveCode(IdUtils.getUUID());
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        //判断校验码是否输入正确
        if (checkcode_session.equals(checkCode)) {
            int rows = userService.addUser(user,request);
            if (rows > 0) {
                return "redirect:/client/registersuccess.jsp";
            } else {
                request.setAttribute("fail","新用户注册失败，请重试！");
                return "/client/register.jsp";
            }
        } else {//校验码不正确
            request.setAttribute("check_error","校验码错误，请重新输入！");
            return "/client/register.jsp";
        }
    }
    //用户激活
    @RequestMapping("/activeUser")
    public String activeUser(String activeCode) {
        System.out.println("激活码为："+activeCode);
        int rows = userService.activeUser(activeCode);
        if (rows > 0) {
            return "redirect:/client/activesuccess.jsp";
        } else {
            return "redirect:/client/activeFail.jsp";
        }
    }

    //检测注册邮箱是否被占用
    @RequestMapping("/findEmail")
    @ResponseBody
    public String findEmail(String email) {
        //System.out.println("注册的邮箱："+email);
        User user = userService.findEmail(email);
        if (user != null) {
            return "EXIST";
        } else {
            return "OK";
        }
    }

    //检测会员名是否已被占用
    @RequestMapping("/findUsername")
    @ResponseBody
    public String findUsername(String username) {
        User user = userService.findUsername(username);
        if (user != null) {
            return "EXIST";
        } else {
            return "OK";
        }
    }

    //我的账户
    @RequestMapping("/myAccount")
    public String myAccount(HttpSession session,HttpServletRequest request) {
        //获取session中登录用户信息
        User login_user = (User) session.getAttribute("login_user");
        if (login_user == null) {       //用户未登录
            //自动登录能否成功，调用自动登录方法
            login_user = autologin(request);
            if (login_user != null) {//如果自动登录成功，返回的login_user就不为空
                //自动登录成功，把登录用户信息放到session
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }
            return "/client/login.jsp"; //点击我的账户到登录界面
        } else {                        //用户已登录
            return "/client/myAccount.jsp"; //显示我的账户信息
        }
    }



    //用户登录
    @RequestMapping("/login")
    public String login(User user, String remember,String autologin, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        //System.out.println("记住用户名："+remember);
        //查询用户名和密码是否存在
        User login_user = userService.findUserByLogin(user);
        if (login_user != null) {//如果用户名和密码正确
            if (login_user.getState() == 1) {//如果用户已激活，则登录成功
                if ("1".equals(autologin)) {
                    //保存用户名和密码到cookie
                    addCookie(autologin,user,request,response);
                } else if ("1".equals(remember)) {
                    //记住用户名，把用户名保存到cookie
                    addCookie(autologin,user,request,response);
                }
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            } else {//用户未激活
                request.setAttribute("login_error","用户未激活，请激活后使用！");
                return "/client/login.jsp";
            }
        } else {
            request.setAttribute("login_error","用户名或密码错误，请重新登录！");
            return "/client/login.jsp";
        }
    }

    //用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response, Model model) {
        //从session中移除登录用户信息
        session.removeAttribute("login_user");
        //从cookie中移除保存的用户名
        Cookie cookie1 = new Cookie("bookstore_username",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie1);
        //从cookie中移除保存的密码
        Cookie cookie2 = new Cookie("bookstore_password",null);
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath()+"/");
        response.addCookie(cookie2);
        model.addAttribute("login_error","用户退出成功，请重新登录！");
        return "/client/login.jsp";
    }

    //用户信息修改
    @RequestMapping("/modifyUser")
    public String modifyUser(User user,HttpSession session,Model model) {
        //System.out.println(user);
        //获取当前登录用户
        User login_user = (User) session.getAttribute("login_user");
        user.setId(login_user.getId());
        int rows = userService.modifyUser(user);
        if (rows > 0) {
            model.addAttribute("login_error","用户信息修改成功，请重新登录！");
            return "/client/login.jsp";
        } else {
            model.addAttribute("fail","用户信息修改失败，请重试！");
            return "/client/modifyuserinfo.jsp";
        }
    }


    //保存用户名和密码
    private void addCookie(String autologin, User user, HttpServletRequest request, HttpServletResponse response) {
        //定义cookie对象:记住用户名到cookie
        Cookie cookie1 = new Cookie("bookstore_username",user.getUsername());
        //保存时间为3天
        cookie1.setMaxAge(60*60*24*3);
        //cookie的作用路径:当前项目下
        cookie1.setPath(request.getContextPath()+"/");
        //把定义好的cookie响应回客户端
        response.addCookie(cookie1);

        if ("1".equals(autologin)) {//定义cookie对象：记住密码到cookie
            Cookie cookie2 = new Cookie("bookstore_password",user.getPassword());
            cookie2.setMaxAge(60*60*24*3);
            cookie2.setPath(request.getContextPath()+"/");
            response.addCookie(cookie2);
        }
    }

    //自动登录
    private User autologin(HttpServletRequest request) {
        //定义字符串用户名和密码为null
        String username = null;
        String password = null;
        //获取全部的cookie信息保存到数据cookies中
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {//循环cookies数据
            //对比找到cookies中保存的bookstore_username的名称
            if ("bookstore_username".equals(cookie.getName())) {
                //把定义bookstore_username的cookie值赋值给上边定义的username
                username = cookie.getValue();
            }
            //对比找到cookies中保存的bookstore_password的名称
            if ("bookstore_password".equals(cookie.getName())) {
                //把定义bookstore_password的cookie值赋值给上边定义的password
                password = cookie.getValue();
            }
        }
        //定义一个user对象作为返回值
        User user = new User();
        //把从cookie中获取的username和password保存到user对象的属性中
        user.setUsername(username);
        user.setPassword(password);
        //根据cookie中获取到的用户名和密码去查询数据库，把查询到的结果返回
        return userService.findUserByLogin(user);
    }

    //订单查询
    @RequestMapping("/findOrderByUser")
    public String findOrderByUser(HttpSession session,Model model) {
        User login_user = (User) session.getAttribute("login_user");
        List<Order> orders = userService.findOrderByUser(login_user.getId());
        model.addAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }

    //查看订单详细信息
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model) {
        List<OrderItem> items = userService.findOrderItemById(id);
        model.addAttribute("items",items);
        return "/client/orderInfo.jsp";
    }

    //删除订单信息
    @RequestMapping("/removeOrderById")
    public String removeOrderById(String id,String flag) {
       userService.removeOrderById(id,flag);
       return "redirect:/client/user/findOrderByUser";
    }
}
