package preparedstatement;

import org.junit.Test;
import util.JDBCUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换 Statement ，实现对数据表的 增删改查 操作
 */

public class UpdateTest {


    // 3. 通用的 增删改 操作
    public void update(String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1. 获取数据库连接
            conn = JDBCUtils.getConnection();
            //2. 预编译SQL语句 返回 PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            //3. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //4. 执行SQL
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5. 关闭资源
            JDBCUtils.closeResourse(conn,ps);
        }

    }

    // 3. 通用方法
    @Test
    public void testCommonUpdate(){
//        String sql = "delete from customers where id = ?";
//        update(sql,3);
        String sql = "update `order` set order_name = ? where order_id = ?";
        update(sql,"DD",2);

    }

    // 2. 修改
    @Test
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1. 获取数据库连接
            connection = JDBCUtils.getConnection();
            //2. 预编译SQL语句 返回 PreparedStatement 的实例
            String sql = "UPDATE customers SET name = ? WHERE id = ?";
            ps = connection.prepareStatement(sql);
            //3. 填充占位符
            ps.setString(1,"无少情");
            ps.setInt(2,18);
            //4. 执行SQL
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5. 关闭资源
            JDBCUtils.closeResourse(connection,ps);
        }
    }

    //1. 向 customers 表中添加一条记录
    @Test
    public void testInsert() {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1. 读取配置文件的四个基本信息
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            // 2. 加载驱动
            Class.forName(driverClass);

            //3. 获取连接
            conn = DriverManager.getConnection(url, user, password);

//        System.out.println(conn);
            //4. 预编译SQL语句，返回 PrepareStatement 实例
            String sql = "INSERT INTO customers(name,email,birth) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            //5. 填充占位符
            ps.setString(1,"哈拉少");
            ps.setString(2,"ksghsldk@gamil.com");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1864-05-13");

            ps.setDate(3,new Date(date.getTime()));

            // 6. 执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //7. 关闭资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
