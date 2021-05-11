package com.li;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // redisTemplate 操作不同的类型
        // opsForValue 操作字符串 类似于 String
        // opsForList 操作list 类似于list
        // ...
        redisTemplate.opsForValue().set("key1","value1");
        System.out.println(redisTemplate.opsForValue().get("key1"));

        //获取连接（少用）
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();

    }


    @Test
    public void test1() throws JsonProcessingException {
        User user = new User("li", 18);
        // 转为json
//        String jsonuser = new ObjectMapper().writeValueAsString(user);

//        redisTemplate.opsForValue().set("user_li",jsonuser);
        redisTemplate.opsForValue().set("user_li",user);

        System.out.println(redisTemplate.opsForValue().get("user_li"));
    }

}
