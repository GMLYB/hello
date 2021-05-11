package com.li.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1","value1");
        jsonObject.put("key2","value2");

        // 开启事务
        Transaction multi = jedis.multi();

        String result = jsonObject.toJSONString();

        try {

            multi.set("user1",result);
            multi.set("user2",result);

            multi.exec(); // 执行事务
        }catch (Exception e){
            e.printStackTrace();
            multi.discard();//放弃事务
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();//关闭事务
        }
    }
}
