package com.li.redis;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    public static void main(String[] args) {
        // 获取对象
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //jedis命令就Redis的命令
        System.out.println(jedis.ping());
    }
}
