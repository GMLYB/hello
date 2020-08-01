package preparedstatement;

import org.junit.Test;
import pojo.Order;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 针对 order表 进行通用的查询
 */

public class OrderForQuery {


    public Order SimpleQuery(String sql, Object ...args) {
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
                Order order = new Order();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    Object colValue = rs.getObject(i + 1);
                    // 获取 列的列名 getColumnName() -----> 不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    // 获取 列的别名 getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,colValue);

                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void testquery1(){
        String sql = "SELECT order_id orderId,order_name orderName,order_date orderDate FROM `order` WHERE order_id = ?";
        Order order = SimpleQuery(sql, 1);
        System.out.println(order);
    }

}
