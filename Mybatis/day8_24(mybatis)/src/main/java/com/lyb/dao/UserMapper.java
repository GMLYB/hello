package com.lyb.dao;

import com.lyb.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList();

    List<User> getUserList2(String name);

    User getUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int updateUser2(Map<String,Object> map);

    int deleteUser(int id);
}
