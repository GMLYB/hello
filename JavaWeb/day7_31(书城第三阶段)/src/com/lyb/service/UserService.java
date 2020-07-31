package com.lyb.service;

import com.lyb.pojo.User;

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    public void registerUser(User user);

    /**
     * 用户登录
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return true 表示用户名存在 ， false 表示用户名可用
     */
    public boolean existUsername(String username);

}
