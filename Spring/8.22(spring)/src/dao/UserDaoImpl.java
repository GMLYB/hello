package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set username = ?, password = ? , email = ? where id = ? ";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(),user.getId());
    }

    @Override
    public int deleteUserById(int id) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public User queryuserById(int id) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where `id` = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    @Override
    public List<User> queryforlist() {
        String sql = "select `id`,`username`,`password`,`email` from t_user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int queryValue() {
        String sql = "select count(*) from t_user";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int ReduceStock(int id) {
        String sql = "update t_book set stock = stock - 1 where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int[] batchUser(List<Object[]> batchargs) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchargs);
        return ints;
    }
}
