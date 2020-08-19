package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Book;
import pojo.Order;
import pojo.User;

public class TestSpring5 {


    @Test
    public void testuser(){
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        //2.获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }

    /**
     * 在spring配置文件配置对象，通过set方法进行配置属性注入
     */
    @Test
    public void testbook1(){
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        //2.获取配置创建的对象
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
    }

    /**
     * 在spring配置文件配置对象，通过set方法进行配置属性注入 p空间
     */
    @Test
    public void testbook2(){
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        //2.获取配置创建的对象
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
    }

    /**
     * 在spring配置文件配置对象，通过有参构造方法进行配置属性注入
     */
    @Test
    public void testorder1(){
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        //2.获取配置创建的对象
        Order order = context.getBean("order", Order.class);
        System.out.println(order);
    }
}
