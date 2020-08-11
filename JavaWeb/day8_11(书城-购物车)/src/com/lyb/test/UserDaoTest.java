package com.lyb.test;

import com.lyb.dao.UserDao;
import com.lyb.dao.impl.UserDaoImpl;
import com.lyb.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
//        System.out.println(userDao.queryUserByUsername("admin"));
        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可以使用");
        }else {
            System.out.println("用户名存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","123456") == null){
            System.out.println("用户名账号或者密码错误");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "admin123", "112233", "user@163.com")));
    }
}