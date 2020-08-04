package test;

import connection.C3P0Connection;
import connection.DBCPConnection;
import connection.DruidConnection;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import pojo.Customer;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class ConnPoolTest {


    @Test
    public void testall() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            conn = C3P0Connection.getConnection1();
//            conn = C3P0Connection.getConnection2();
//            conn = DBCPConnection.getConnection1();
//            conn = DBCPConnection.getConnection2();

            conn = DruidConnection.getConnection();
            String sql = "select id,`name`,email,birth from customers where id = ?";

            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);

            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps, rs);
        }

    }

    /*----------------------使用德鲁伊和dbutils进行对数据库的操作---------------------------------*/

    //查询一条数据
    @Test
    public void test1() {
        Connection conn = null;
        try {
            conn = DruidConnection.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,`name`,email,birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(conn, sql, handler, 18);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    //查询一个list数据
    @Test
    public void test2(){

        Connection conn = null;
        try {
            conn = DruidConnection.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,`name`,email,birth from customers where id < ?";
            BeanListHandler handler = new BeanListHandler(Customer.class);
            List<Customer> list = (List<Customer>) runner.query(conn, sql, handler, 20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }

    @Test
    public void test3() {

        Connection conn = null;
        try {
            conn = DruidConnection.getConnection();

            QueryRunner runner = new QueryRunner();

            String sql = "update customers set name = ? where id = ?";

            int i = runner.update(conn, sql, "冯绍峰", 18);
            System.out.println("有"+ i + "条记录被修改");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void test4() {

        Connection conn = null;
        try {
            conn = DruidConnection.getConnection();

            QueryRunner runner = new QueryRunner();

            String sql = "select count(*) from customers";

            ScalarHandler handler = new ScalarHandler();

            Long count = (Long) runner.query(conn, sql, handler);

            System.out.println("共有"+count+"条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }

    //查询一条数据
    @Test
    public void test5() {
        Connection conn = null;
        try {
            conn = DruidConnection.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,`name`,email,birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 18);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

}
