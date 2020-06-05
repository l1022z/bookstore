package com.bookstore.admin.login.dao;

import com.bookstore.commons.beans.User;

public interface IAdminUserDao {
    User selectUserByLogin(User user);
}
