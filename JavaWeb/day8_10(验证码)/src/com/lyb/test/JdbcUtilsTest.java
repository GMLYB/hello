package com.lyb.test;

import com.lyb.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        //配置文件只配置最大连接数为10， 因此只有释放连接才可以继续使用
        for (int i = 0; i < 100; i++) {
//            System.out.println(JdbcUtils.getConnection());
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);

        }
    }
}
