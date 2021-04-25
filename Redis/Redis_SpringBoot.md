## Redis

### 1 Springboot整合

* 在Springboot 2.x之后，原来使用的Jedis被替换为lettuce
  * Jedis：采用直连方式，多线程操作不安全，应该使用Jedis pool连接池。像 BIO 模式
  * lettuce：采用netty，实例可以在多个线程中进行共享，不存在线程安全问题，可以减少线程数据，更像 NIO模式



#### 1.1 使用步骤

* 导入依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

* 配置连接

```xml
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

* 测试

```java
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

}
```

