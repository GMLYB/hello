package connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;

public class C3P0Connection {

    //方式一：硬编码
    public static Connection getConnection1() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jbdc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql:///test" );
        cpds.setUser("root");
        cpds.setPassword("root");
        return cpds.getConnection();

    }

    //方式二：配置文件
    public static ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0config");
    public static Connection getConnection2() throws Exception {
        Connection conn = cpds.getConnection();

        return conn;
    }

}
