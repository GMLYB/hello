package com.lyb.test;

import com.lyb.config.SpringConfig;
import com.lyb.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService service = context.getBean("userService", UserService.class);
        service.add();
        service.showname();
    }

    @Test
    public void test2(){
        //加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService service = context.getBean("userService", UserService.class);
        service.add();
        service.showname();
    }

}
