package com.mingg.minggblog.service;

import com.mingg.minggblog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
