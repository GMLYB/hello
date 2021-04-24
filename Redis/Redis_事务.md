## Redis

### 1 事务

* Redis单条命令是保证原子性的，但是Redis的事务不保证原子性的。

* Redis事务本质：一组命令的集合，一个事务中的所有命令都会被序列化，在事务执行过程中，会按照顺序执行。

* Redis事务没有隔离级别的概念，所有的命令在事务中，没有被直接执行，只有发起命令的时候才会执行。

* Redis事务：

  * 开启事务：multi
  * 命令入队：...
  * 执行事务：exec

  ```
  正常执行事务：
  127.0.0.1:6379> multi #开启事务
  OK
  127.0.0.1:6379(TX)> set k1 v1 #命令入队
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> get k2
  QUEUED
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> exec #执行事务
  1) OK
  2) OK
  3) "v2"
  4) OK
  ```

  * 放弃事务

  ```
  127.0.0.1:6379> multi #开启事务
  OK
  127.0.0.1:6379(TX)> set k4 v4 #命令入队
  QUEUED
  127.0.0.1:6379(TX)> set k5 v5
  QUEUED
  127.0.0.1:6379(TX)> discard #放弃事务
  1) OK
  127.0.0.1:6379> get key4
  (nil)
  ```

  * 编译型异常（命令有错），事务中所有的命令都不会被执行。

  ```
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> getset k3 
  (error) ERR wrong number of arguments for 'getset' command
  127.0.0.1:6379(TX)> set k4 v4
  QUEUED
  127.0.0.1:6379(TX)> exec
  (error) EXECABORT Transaction discarded because of previous errors.
  127.0.0.1:6379> get key4
  (nil)
  ```

  * 运行时异常（例如：1/0），事务队列中存在语法性错误，那么执行命令的时候，其他命令可以正常执行。错误命令抛出异常。

  ```
  127.0.0.1:6379> set k1 "v1"
  OK
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> incr k1
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> exec
  1) (error) ERR value is not an integer or out of range
  2) OK
  3) OK
  127.0.0.1:6379> get k2
  "v2"
  ```



### 2 监控

* Watch

* 悲观锁：什么时候都会出现问题，无论什么都会加锁。

* 乐观锁

  * 什么时候都不会出现问题，所以不会上锁。更新数据的时候，在此期间是否有人修改数据，version字段
  * 获取version
  * 更新时候比较version

  > 正常情况下

  ```
  127.0.0.1:6379> set money 100
  OK
  127.0.0.1:6379> set out 0
  OK
  127.0.0.1:6379> watch money
  OK
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> decrby money 20
  QUEUED
  127.0.0.1:6379(TX)> incrby out 20
  QUEUED
  127.0.0.1:6379(TX)> exec
  1) (integer) 80
  2) (integer) 20
  ```

  > 多个客户端访问同一个key的时候

  ```
  127.0.0.1:6379> watch money #监控
  OK
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> decrby money 20
  QUEUED
  127.0.0.1:6379(TX)> incrby out 20
  QUEUED
  127.0.0.1:6379(TX)> exec # 执行之前，另外一个客户端修改了money的值
  (nil)
  ```

  > 解锁：unwatch。执行exec或者discard后，会自动释放锁，unwatch释放全部锁。



### 3 Jedis

* Jedis是Redis官方推荐的Java连接开发工具，是使用Java操作Redis的中间件。

#### 3.1 导入对应依赖

```

<dependencies>
	<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
	<dependency>
		<groupId>redis.clients</groupId>
		<artifactId>jedis</artifactId>
		<version>3.3.0</version>
	</dependency>

	<!--fastjson-->
	<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>fastjson</artifactId>
		<version>1.2.75</version>
	</dependency>
</dependencies>
```



#### 3.2 编码测试

* 获取连接

```java
public class RedisConnection {
    public static void main(String[] args) {
        // 获取对象
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //jedis命令就Redis的命令
        System.out.println(jedis.ping()); // PONG
    }
}
```

*  常用API

  * String
  * List
  * Set
  * Hash
  * Zset

  ```java
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
      }
  }
  
  清空数据：OK
  判断某个值是否存在：false
  新增key1和value1：OK
  新增key2和value2：OK
  展示所有Key：[key1, key2]
  删除value1：1
  判断key2类型：string
  随机返回一个Key：key2
  重命名key2：OK
  按索引查询：OK
  返回key的数量1
  ```



#### 3.3 事务

```java
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
```

