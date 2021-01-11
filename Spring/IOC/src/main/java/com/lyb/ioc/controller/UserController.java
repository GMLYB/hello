package com.lyb.ioc.controller;

import com.lyb.ioc.annotion.MyAutowired;
import com.lyb.ioc.annotion.MyComponent;
import com.lyb.ioc.service.UserService;

@MyComponent
public class UserController {

    @MyAutowired
    UserService userService;

    public void addUser(){
        userService.addUser();
        System.out.println("add user successful");
    }
}
