package com.bookstore.admin.login.service;

import com.bookstore.admin.login.dao.IAdminUserDao;
import com.bookstore.commons.beans.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Resource
    IAdminUserDao adminUserDao;
    @Override
    public User findUserByLogin(User user) {
        return adminUserDao.selectUserByLogin(user);
    }
}
