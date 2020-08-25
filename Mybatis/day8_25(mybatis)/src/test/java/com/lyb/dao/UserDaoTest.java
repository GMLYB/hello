package com.lyb.dao;

import com.lyb.pojo.User;
import com.lyb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    static Logger logger = Logger.getLogger(UserDaoTest.class);


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
    public void testlog4j(){
        logger.info("info:进入了testlog4j");
        logger.debug("debug:进入了testlog4j");
        logger.error("error:进入了testlog4j");
    }

}
