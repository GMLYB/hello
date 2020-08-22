package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TestUser {

    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

    @Test
    public void addTest(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User user = new User(1,"lixs","112233","dshkf@qq.com");
        System.out.println(userService.addUser(user));
    }

    @Test
    public void updateTest(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User user = new User(11,"lixs","aabbcc","ffffhkf@qq.com");
        System.out.println(userService.updateUser(user));
    }

    @Test
    public void deleteTest(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.deleteUser(11));
    }

    @Test
    public void queryOne(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.queryOne(10));
    }

    @Test
    public void queryList(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        List<User> list = userService.queryList();
        list.forEach(System.out::println);
    }

    @Test
    public void queryValue(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        int count = userService.queryUserCount();
        System.out.println(count);
    }

    @Test
    public void batchAddUser(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        List<Object[]> batchargs = new ArrayList<>();
        Object[] o1 = {"li1","aabbcc","li1qdq@qq.com"};
        Object[] o2 = {"li2","aa22cc","li2qdq@qq.com"};
        Object[] o3 = {"li3","aabb33","li3qdq@qq.com"};
        batchargs.add(o1);
        batchargs.add(o2);
        batchargs.add(o3);
        int[] ints = userService.batchAdd(batchargs);
        System.out.println(ints.length);
    }

    @Test
    public void txtest(){
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        int[] id = new int[]{10,11};
        int count = userService.BuyOneBook(id);
        System.out.println(count);
    }
}
