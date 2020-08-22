package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)//单元测试框架
@ContextConfiguration("classpath:bean.xml")// 加载配置文件
public class JTest4 {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        int count = userService.queryUserCount();
        System.out.println(count);//12
    }
}
