package com.bookstore.admin.login.service;

import com.bookstore.commons.beans.User;

public interface IAdminUserService {
    User findUserByLogin(User user);
}
