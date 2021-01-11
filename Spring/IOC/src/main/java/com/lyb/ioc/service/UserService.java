package com.lyb.ioc.service;


import com.lyb.ioc.annotion.MyAutowired;
import com.lyb.ioc.annotion.MyComponent;
import com.lyb.ioc.dao.UserDao;

@MyComponent
public class UserService {

    @MyAutowired
    UserDao userDao;

    public void addUser(){
        userDao.UserAdd();
        System.out.println("add user method");
    }
}
