package com.lyb.service;

import com.lyb.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 在注解里面value属性值可以省略不写
 * 默认值是类的名称，首字母小写 UserService --> userService
 */
@Component(value = "userService") //<bean id="userService" class="..">
public class UserService {

    /*
    @Autowired
    @Qualifier(value = "UserDaoImpl1")
    private UserDao userDao;
    */
//    @Resource
    @Resource(name = "UserDaoImpl1")
    private UserDao userDao;

    @Value(value = "abc")
    private String name;

    public void add(){
        System.out.println("userservice add....");
        userDao.add();
    }

    public void showname(){
        System.out.println(name);
    }
}
