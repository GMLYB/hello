package com.lyb.mvc.service;

import com.lyb.mvc.dao.UserDao;
import com.lyb.mvc.pojo.UserBean;
import com.lyb.mvc.utils.Eutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean addUser(UserBean user) {
        Eutil.validate(user);
        return userDao.addUser(user);
    }

}