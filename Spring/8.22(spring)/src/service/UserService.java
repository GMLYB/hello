package service;

import pojo.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

    User queryOne(int id);

    List<User> queryList();

    int queryUserCount();

    int BuyOneBook(int[] id);

    int[] batchAdd(List<Object[]> batchargs);
}
