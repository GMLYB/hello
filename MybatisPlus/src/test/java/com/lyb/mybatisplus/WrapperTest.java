package com.lyb.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyb.mybatisplus.mapper.UserMapper;
import com.lyb.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1(){
        //查询name不为空，邮箱不为空，年龄大于等于18岁
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",18);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void test2(){
        // id 等于 "2"
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",2L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void test3(){
        //年龄在 18-20 之间的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",18,20);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);

    }


    @Test
    public void test4(){
        /*
            模糊查询
            左 右 %e%
         */
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","J")
                .likeRight("email","mao");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }

    @Test
    public void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id 通过子查询查询出来
        wrapper.inSql("id","select id from user where id < 3");
        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test6(){
        // 排序 通过年龄进行排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
//        wrapper.orderByAsc("age");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
