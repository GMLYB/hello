package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Book;
import pojo.Course;
import pojo.Order;
import pojo.Student;

import java.sql.Connection;
import java.sql.SQLException;

public class TestSpring {

    @Test
    public void testCollection1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
    @Test
    public void testCollection2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    @Test
    public void testCollection3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }


    @Test
    public void testbeanfactory(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        Course mybean = context.getBean("mybean", Course.class);
        System.out.println(mybean);
    }

    @Test
    public void testbook1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        Book book1 = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);
        System.out.println(book1);
        System.out.println(book2);
    }

    @Test
    public void testorder1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println("bean生命周期第 四 步：获取创建实例对象");
        //手动销毁context
        ((ClassPathXmlApplicationContext) context).close();
    }

    @Test
    public void testjdbc1() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource);
    }


    @Test
    public void testjdbc2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean8.xml");
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource);
    }

}
