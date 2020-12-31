package com.mybatisdemo.service;

import com.mybatisdemo.mapper.UserLoginMapper;
import com.mybatisdemo.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserLoginServicesImpl implements UserLoginServices {
    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> queryAll() {
        return userLoginMapper.queryAll();
    }

    @Override
    @Transactional
    public int add(UserLogin userLogin) {
        return userLoginMapper.add(userLogin);
    }

    @Override
    @Transactional
    public UserLogin queryByName(String userName) {
        UserLogin login = new UserLogin();
        login.setUserName("abc");
        login.setPassword("abc");
        this.add(login);
        return userLoginMapper.queryByName(userName);
    }
}
