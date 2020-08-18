package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Book;
import pojo.Order;
import pojo.User;
import service.UserServiceImpl;

public class TestBean {


    @Test
    public void testuser(){
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        //2.获取配置创建的对象
        UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);
        userService.add();
    }

}
