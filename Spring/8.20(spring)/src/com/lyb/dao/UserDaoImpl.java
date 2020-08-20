package com.lyb.dao;

import org.springframework.stereotype.Repository;

@Repository(value = "UserDaoImpl1")
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("UserDao add...");
    }
}
