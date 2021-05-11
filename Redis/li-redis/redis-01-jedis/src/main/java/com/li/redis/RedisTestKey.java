package com.li.redis;

import redis.clients.jedis.Jedis;

public class RedisTestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println("清空数据：" + jedis.flushDB());
        System.out.println("判断某个值是否存在：" + jedis.exists("key1"));
        System.out.println("新增key1和value1：" + jedis.set("key1","value1"));
        System.out.println("新增key2和value2：" + jedis.set("key2","value2"));
        System.out.println("展示所有Key：" + jedis.keys("*"));

        System.out.println("删除value1：" + jedis.del("key1"));
        System.out.println("判断key2类型：" + jedis.type("key2"));
        System.out.println("随机返回一个Key：" + jedis.randomKey());
        System.out.println("重命名key2：" + jedis.rename("key2","key22"));
        System.out.println("按索引查询：" + jedis.select(0));
        System.out.println("返回key的数量" + jedis.dbSize());
        jedis.close();
    }
}
