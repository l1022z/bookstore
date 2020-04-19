package com.bookstore.client.user.service;

import com.bookstore.client.user.dao.IUserDao;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/4/15
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserDao userDao;
    @Override
    public int addUser(User user, HttpServletRequest request) {
        String emailMsg = "感谢您注册网上书城，请点击" +
                "<a href='http://localhost:8080/"+request.getContextPath()+"/client/user/activeUser?activeCode=" + user.getActiveCode()+"'>激活</a>后使用！";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.insertUser(user);
    }

    @Override
    public int activeUser(String activeCode) {
        return userDao.activeUser(activeCode);
    }

    @Override
    public User findEmail(String email) {
        return userDao.selectEmail(email);
    }

    @Override
    public User findUsername(String username) {
        return userDao.selectUsername(username);
    }

    @Override
    public User findUserByLogin(User user) {
        return userDao.selectUserByLogin(user);
    }
}
