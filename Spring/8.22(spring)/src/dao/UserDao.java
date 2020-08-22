package dao;

import pojo.User;

import java.util.List;

public interface UserDao {


    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(int id);

    User queryuserById(int id);

    List<User> queryforlist();

    int queryValue();

    int ReduceStock(int id);

    int[] batchUser(List<Object[]> batchargs);

}
