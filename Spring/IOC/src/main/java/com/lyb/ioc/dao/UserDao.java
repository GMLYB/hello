package com.lyb.ioc.dao;

import com.lyb.ioc.annotion.MyComponent;

@MyComponent
public class UserDao {
    public void UserAdd(){
        System.out.println("add user to Database");
    }
}

