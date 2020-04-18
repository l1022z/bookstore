package com.bookstore.client.user.service;

import com.bookstore.client.user.dao.IUserDao;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int addUser(User user) {
        return userDao.insertUser(user);
    }
}
