package com.mingg.minggblog.service.impl;

import com.mingg.minggblog.dao.UserRepository;
import com.mingg.minggblog.po.User;
import com.mingg.minggblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkUser(String username, String password) {
        //Spring自带的DigestUtils实现md5码加密
        //String psd = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        User user = userRepository.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
