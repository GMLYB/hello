package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 5,rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public User queryOne(int id) {
        return userDao.queryuserById(id);
    }

    @Override
    public List<User> queryList() {
        return userDao.queryforlist();
    }

    @Override
    public int queryUserCount() {
        return userDao.queryValue();
    }

    @Override
    public int BuyOneBook(int[] id) {
        int count = 0;
        for (int i = 0; i < id.length; i++) {
            count += userDao.ReduceStock(id[i]);
        }
        int x = 10 / 0;

        return count;
    }

    @Override
    public int[] batchAdd(List<Object[]> batchargs) {
        return userDao.batchUser(batchargs);
    }
}
