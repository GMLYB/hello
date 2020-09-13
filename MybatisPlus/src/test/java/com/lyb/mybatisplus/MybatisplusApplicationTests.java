package com.lyb.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyb.mybatisplus.mapper.UserMapper;
import com.lyb.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.*;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Test
    public void querylist(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void insertTest(){
        User user = new User();
        user.setName("毛二");
        user.setAge(18);
        user.setEmail("maoer@mao.com");
        int num = userMapper.insert(user);
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setName("毛三");
        user.setId(1305133382929235969L);
        userMapper.updateById(user);
    }

    //乐观锁：修改成功
    @Test
    public void undateTest1(){
        User user = userMapper.selectById(6L);
        user.setName("毛三111");
        userMapper.updateById(user);
    }

    //乐观锁：修改失败
    @Test
    public void undateTest2(){
        User user1 = userMapper.selectById(6L);
        user1.setName("毛三222");

        User user2 = userMapper.selectById(6L);
        user2.setName("毛三333");
        userMapper.updateById(user2);

        userMapper.updateById(user1);
    }

    //单项查询
    @Test
    public void query1(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    //多项查询
    @Test
    public void query2(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<User> list = userMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }
    //使用map多项查询
    @Test
    public void query3(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","毛三");
        map.put("age",19);
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    //分页
    @Test
    public void testPage(){
        Page<User> page = new Page<>(1,5);

        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        //总数量
        System.out.println(page.getTotal());

    }

    @Test
    public void delete1(){
        userMapper.deleteById(1305053379105447937L);
    }

    @Test
    public void delete2(){
        userMapper.deleteBatchIds(Arrays.asList(6L,1305133382929235969L));
    }

    @Test
    public void delete3(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","毛三");
        userMapper.deleteByMap(map);
    }

    //逻辑删除
    @Test
    public void delete4(){
        userMapper.deleteById(1L);
    }

    @Test
    public void querytest(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }




}
