package com.lyb.service.impl;

import com.lyb.dao.UserDao;
import com.lyb.dao.impl.UserDaoImpl;
import com.lyb.pojo.User;
import com.lyb.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();


    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {

        if (userDao.queryUserByUsername(username) == null){
            //没查到表示可以使用
            return false;
        }
        return true;
    }
}
