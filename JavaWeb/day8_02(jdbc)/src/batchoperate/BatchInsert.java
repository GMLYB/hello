package batchoperate;


/*

   使用 PreparedStatement 进行批量插入

 */

import org.junit.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchInsert {

    //方式一：
    @Test
    public void testInsert1() {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "insert into goods(`name`) values (?)";
            ps = conn.prepareStatement(sql);

            long start = System.currentTimeMillis();

            for (int i = 1; i <= 10000; i++) {
                ps.setObject(1,"name" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();

            System.out.println("1w条数据插入使用时间:" + (end - start));//1w条数据插入使用时间:382836


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps);
        }

    }


    /*
        1.addBatch() executebatch() clearBatch()
        2.mysql服务器默认关闭批量处理 需要添加一个参数，让mysql开启批量处理支持
            ?rewriteBatchedStatements=true 写在配置文件 url后面
         3.使用更新驱动  mysql-connector-java-5.1.37-bin.jar
     */
    @Test
    public void testInsert2() {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values (?)";
            ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();

            for (int i = 1; i <= 10000; i++) {
                ps.setObject(1,"name" + i);

                //1. 攒SQL
                ps.addBatch();

                if (i % 500 == 0){
                    //2. 执行batch
//                    System.out.println("执行一次");
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
            }

            long end = System.currentTimeMillis();

            System.out.println("1w条数据插入使用时间:" + (end - start));//1w条数据插入使用时间:380938


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps);
        }

    }
    /*
            //1. 设置不自动提交数据
            conn.setAutoCommit(false);
            //设置提交
            conn.commit();
     */

    @Test
    public void testInsert3() {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            //1. 设置不自动提交数据
            conn.setAutoCommit(false);
            String sql = "insert into goods(name) values (?)";
            ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1,"name" + i);

                //1. 攒SQL
                ps.addBatch();

                if (i % 500 == 0){
//                    System.out.println("执行一次");
                    //2. 执行batch
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
            }
            conn.commit();

            long end = System.currentTimeMillis();

            System.out.println("1w条数据插入使用时间:" + (end - start));//1w条数据插入使用时间:2068


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps);
        }

    }

}
