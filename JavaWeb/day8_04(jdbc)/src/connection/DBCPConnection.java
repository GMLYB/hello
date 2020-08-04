package connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPConnection {

    //方式一
    public static Connection getConnection1() throws Exception {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql:///test");
        source.setUsername("root");
        source.setPassword("swflhqlyb123");

        source.setInitialSize(10);

        return source.getConnection();
    }


    //使用dbcp数据库连接池的配置文件方式，获取数据库的连接：推荐
    private static DataSource source = null;
    static{
        try {
            Properties pros = new Properties();

//            InputStream is = DBCPConnection.class.getClassLoader().getResourceAsStream("dbcp.properties");
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));

            pros.load(is);
            //根据提供的BasicDataSourceFactory创建对应的DataSource对象
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection2() throws Exception {

        Connection conn = source.getConnection();

        return conn;
    }

}
