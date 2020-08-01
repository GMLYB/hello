package preparedstatement;

import org.junit.Test;
import pojo.Customer;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;

public class CustomerForQuery {

    public Customer querycustomer(String sql, Object ...args) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();

            //通过 getMetaData 获取元数据，getMetaData 对象可以获取列的数量，列名等信息
            ResultSetMetaData metaData = rs.getMetaData();

            if (rs.next()){
                Customer cust = new Customer();
                //通过 getMetaData 对象获取列的数量
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 获取每一列的值
                    Object columValue = rs.getObject(i + 1);
                    //获取每一列的列名  ---> 不推荐
//                    String columnName = metaData.getColumnName(i + 1);
                    //获取列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //通过反射机制，得到和 列名 一致的对象的属性
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    //设置私有属性可以访问(不要漏)
                    field.setAccessible(true);
                    //给对象的属性进行赋值
                    field.set(cust,columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(connection,ps,rs);
        }
        return null;
    }

    @Test
    public void testCommonQuery(){
//        String sql = "select id,`name`,email,birth from customers where id = ?";
        String sql = "select id,`name` from customers where id = ?";
        Customer customer = querycustomer(sql, 1);
        System.out.println(customer);
    }

    @Test
    public void testQuery1() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1. 获取连接对象
            conn = JDBCUtils.getConnection();
            //2. 编写SQL语句，获得 PreparedStatement 对象
            String sql = "select id,`name`,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            //3. 设置占位符
            ps.setObject(1,1);
            //4. 执行SQL，并获得结果集
            resultSet = ps.executeQuery();
            //next()方法判断是否还存在数据
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5. 关闭资源
            JDBCUtils.closeResourse(conn,ps,resultSet);
        }
    }



}
