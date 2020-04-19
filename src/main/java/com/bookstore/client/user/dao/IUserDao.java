package com.bookstore.client.user.dao;

import com.bookstore.commons.beans.User;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/4/15
 */
public interface IUserDao {
    int insertUser(User user);

    int activeUser(String activeCode);

    User selectEmail(String email);

    User selectUsername(String username);

    User selectUserByLogin(User user);
}
