package com.mybatisdemo.service;

import com.mybatisdemo.model.UserLogin;

import java.util.List;

public interface UserLoginServices {
    //查询
    List<UserLogin> queryAll();

    //添加数据
    int add(UserLogin userLogin);

    //根据用户名查询数据
    UserLogin queryByName(String userName);
}
