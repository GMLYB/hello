package transaction;

import org.junit.Test;
import pojo.User;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class TransactionTest {


    @Test
    public void testSelect() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        //获取当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        //取消自动提交
        conn.setAutoCommit(false);

        String sql = "select user,password,balance from user_table WHERE user = ?";
        User user = CommonQuery(conn, User.class, sql, "CC");
        System.out.println(user);
    }


    @Test
    public void testUpdate() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        //取消自动提交
        conn.setAutoCommit(false);

        String sql = "UPDATE user_table SET balance = ? WHERE user = ?";
        update(conn, sql, 2000, "CC");
        Thread.sleep(15000);
        System.out.println("修改结束");
    }


    //通用的增、删、改操作
    public void update(Connection conn,String sql, Object ...args){
        PreparedStatement ps = null;
        try {
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
            JDBCUtils.closeResourse(null,ps);
        }
    }


    // 获取一个对象的方法
    public <T> T CommonQuery(Connection conn,Class<T> tClass ,String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtils.closeResourse(null,ps,rs);
        }
        return null;
    }
}
