package blob;

import org.junit.Test;
import util.JDBCUtils;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用 PreparedStatement 操作 Blob 类型的数据
 */

public class BlobTest {



    //1. 向数据表customers表插入一个Blob数据
    @Test
    public void testInsert() {

        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO customers(`name`,email,birth,photo) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,"建筑物");
            ps.setObject(2,"sence@qq.com");
            ps.setObject(3,"1988-08-18");

            fis = new FileInputStream(new File("background.jpg"));

            ps.setBlob(4,fis);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn,ps);
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //2. 查询 数据库中的 blob操作
    @Test
    public void testQuery() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT `name`,photo FROM customers WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,22);

            rs = ps.executeQuery();



            if (rs.next()){
                String name = rs.getString(1);
                Blob photo = rs.getBlob(2);
                System.out.println("name = " + name);

                is = photo.getBinaryStream();

                fos = new FileOutputStream("girl1.jpg");

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResourse(conn,ps,rs);
        }




    }
}
