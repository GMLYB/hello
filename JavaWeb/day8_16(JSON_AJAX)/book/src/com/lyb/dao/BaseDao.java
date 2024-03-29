package com.lyb.dao;

import com.lyb.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用 Dbutils 操作数据库
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * update() 方法用来执行：insert、update、delete 语句
     * @return 返回-1说明执行失败，返回其他表示影响的行数
     */
    public int update(String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            //返回影响的行数
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个 JavaBean 的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */

    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个 JavaBean 的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */

    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 执行返回一行一列的 SQL 语句
     * @param sql SQL语句
     * @param args SQL对应的参数
     * @return
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
