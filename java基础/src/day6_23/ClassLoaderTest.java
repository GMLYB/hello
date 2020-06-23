package day6_23;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {

    /**
     * properties:用来读取配置文件
     *
     */
    @Test
    public void test1() throws Exception {
        Properties pros = new Properties();
        //此时的文件默认在当前的module下
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("src\\day6_23\\jdbc.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("day6_23\\jdbc.properties");
        pros.load(is);

        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println("用户名："+name);
        System.out.println("密码："+password);

    }
}
