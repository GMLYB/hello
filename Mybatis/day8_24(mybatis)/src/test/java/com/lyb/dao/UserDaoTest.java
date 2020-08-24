package com.lyb.dao;

import com.lyb.pojo.User;
import com.lyb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void testgetUserList(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userDao.getUserList();
            userList.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testgetUserList2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userDao.getUserList2("li");
            userList.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testgetUserById(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(10);
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //增删改必须要提交事务
    @Test
    public void testaddUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.addUser(new User(null, "wuqing", "aassdd", "wuqing@qq.com"));
            System.out.println(i);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //增删改必须要提交事务
    @Test
    public void testupdateUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.updateUser(new User(27, "youqing", "aaggdd", "wuqing@qq.com"));
            System.out.println(i);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //增删改必须要提交事务
    @Test
    public void testupdateUser2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<>();
            map.put("userid",21);
            map.put("name","l1001");
            map.put("pwd","qqwwee");
            map.put("useremail","qqwwee@qq.com");
            int i = mapper.updateUser2(map);
            System.out.println(i);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //增删改必须要提交事务
    @Test
    public void testdeleteUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.deleteUser(27);
            System.out.println(i);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
