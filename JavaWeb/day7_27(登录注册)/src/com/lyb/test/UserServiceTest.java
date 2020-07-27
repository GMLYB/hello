package com.lyb.test;

import com.lyb.pojo.User;
import com.lyb.service.UserService;
import com.lyb.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"admin02","aabb22","admin02@126.com"));
        userService.registerUser(new User(null,"admin03","aabb22","admin03@126.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "admin02", "aabb22", "admin02@126.com")));
        System.out.println(userService.login(new User(null, "admin052", "aabb22", "admin02@126.com")));
    }

    @Test
    public void existUsername() {
        System.out.println(userService.existUsername("admin02"));
        System.out.println(userService.existUsername("admin0f2"));
    }
}