package com.bookstore.client.user.service;

import com.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/4/15
 */
public interface IUserService {
    int addUser(User user, HttpServletRequest request);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User findUserByLogin(User user);
}
