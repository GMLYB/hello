package preparedstatement;

import org.junit.Test;
import pojo.Customer;
import pojo.Order;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 PrepareStament 针对不同表的 通用的 查询操作
 */

public class PrepareStamentQuery {


    @Test
    public void test2(){
        String sql = "select id,`name` from customers where id < ?";
        List<Customer> list = CommonQueryForlist(Customer.class, sql, 10);

        //lambda表达式
        list.forEach(System.out::println);
    }



    // 获取一个list对象的方法
    public <T> List<T> CommonQueryForlist(Class<T> tClass , String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            List<T> list = new ArrayList<>();

            while (rs.next()){
                T t = tClass.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    Object colValue = rs.getObject(i + 1);
                    // 获取 列的列名 getColumnName() -----> 不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    // 获取 列的别名 getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = tClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,colValue);

                }
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void test1(){
        String sql = "SELECT order_id orderId,order_name orderName,order_date orderDate FROM `order` WHERE order_id = ?";
        Order order = CommonQuery(Order.class, sql, 1);
        System.out.println(order);

        String sql1 = "select id,`name` from customers where id = ?";
        Customer customer = CommonQuery(Customer.class, sql1, 2);
        System.out.println(customer);

    }

    // 获取一个对象的方法
    public <T> T CommonQuery(Class<T> tClass ,String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            if (rs.next()){
                T t = tClass.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    Object colValue = rs.getObject(i + 1);
                    // 获取 列的列名 getColumnName() -----> 不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    // 获取 列的别名 getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field field = tClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,colValue);

                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps,rs);
        }
        return null;
    }

}
