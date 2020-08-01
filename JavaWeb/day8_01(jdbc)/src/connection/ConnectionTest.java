package connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    //方式一
    @Test
    public void testConnection1() throws SQLException {
        //1. 获取Driver的实现类对象
        Driver driver = new com.mysql.jdbc.Driver();
        //2. 提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //3. 将用户名和密码封装到 Properties 中
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        //4. 获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    //方式二 --> 方式一的优化 --> 不出现第三方的api，使得程序具有更好的可移植性
    @Test
    public void testConnection2() throws Exception {

        //获取Driver实现类对象，通过反射
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        //2. 提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //3. 将用户名和密码封装到 Properties 中
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        //4. 获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    //方式三：使用 DriverManager 替换 Driver
    @Test
    public void testConnection3() throws Exception {
        //1. 提供三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        //2. 获取Driver实现类对象，通过反射
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();


        //注册驱动
        DriverManager.registerDriver(driver);
        // 3. 通过 DriverManager  获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    //方式四：加载驱动，不需要注册启动
    /*
        相较于方式三 ，可以省略如下步骤 ，因为Class.forName() 对 Driver 进行加载，
        并通过静态代码块进行的对Driver的初始化
        Driver driver = (Driver) aClass.newInstance();
        //注册驱动
        DriverManager.registerDriver(driver);
     */
    @Test
    public void testConnection4() throws Exception {
        //1. 提供三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        //2. 加载Driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. 通过 DriverManager  获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式五：将数据库连接需要的参数声明在配置文件，通过读取配置文件的方式，获取连接参数

    /*
     * 好处：
     * 1. 实现了数据与代码的分离，实现了解耦
     * 2. 如果需要修改配置文件信息，避免程序的重新打包
     *
     */
    @Test
    public void testConnection5() throws Exception {
        //1. 读取配置文件的四个基本信息
        InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();

        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        // 2. 加载驱动
        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);

    }
}
