## Redis

### 1 五大数据类型

#### Redis-Key

* `exists key`：判断key是否存在，存在为1，不存在为0。
* `move key number `：将Key移动到指定数据库。`move key 1`
* `expire key time`：设置过期时间。`expire key 10`
* `ttl key`：查询key剩余时间
* `key *`：查看所有的key
* `del key`：将key从当前数据库中移除
* `type key`：查看key所存储的值的类型



#### 1.1 String

* 设置值：set key1 value1 --> ok

* 获取值：get key1 --> value1

* 获取所有key：keys * --> key1

* 判断某一个ky是否存在：exists key1 --> 1

* 追加字符串（如果当前Key不存在，相当于set key）：append key1 value2 --> value1value2

* 获取字符串长度：strlen key1 --> 12

* 自动增长/减少：incr/decr key || incrby/decrby key num。

  * ```java
    127.0.0.1:6379> set views 0
    OK
    127.0.0.1:6379> get views
    "0"
    127.0.0.1:6379> incr views
    (integer) 1
    127.0.0.1:6379> incr views
    (integer) 2
    127.0.0.1:6379> get views
    "2"
    127.0.0.1:6379> decr views
    (integer) 1
    127.0.0.1:6379> decr views
    (integer) 0
    127.0.0.1:6379> incrby views 10
    (integer) 10
    127.0.0.1:6379> decrby views 15
    (integer) -5
    ```

* 字符串范围

  * ```java
    #截取字符串： getrange || substr
    127.0.0.1:6379> get key1
    "value1value2"
    127.0.0.1:6379> substr key1 1 3
    "alu"
    127.0.0.1:6379> getrange key1 1 3
    "alu"
    127.0.0.1:6379> substr key1 1 -1
    "alue1value2"
    127.0.0.1:6379> getrange key1 1 -1
    "alue1value2"
        
    #替换指定位置开始的字符串：setrange
    127.0.0.1:6379> get key1
    "value1value2"
    127.0.0.1:6379> setrange key1 2 cc
    (integer) 12
    127.0.0.1:6379> get key1
    "vacce1value2"
    
    ```

* 其他设置

  * 设置过期时间：setex（set with expire）

    * ```
      127.0.0.1:6379> setex key3 30 value3 #设置key3值为value3,30s过期
      OK
      127.0.0.1:6379> get key3
      "value3"
      127.0.0.1:6379> ttl key3
      (integer) 18
      127.0.0.1:6379> ttl key3
      (integer) -2
      127.0.0.1:6379> get key3
      (nil)
      ```

  * 不存在再设置：setnx（set if not exist）-->分布式锁中常常使用

    * ```
      127.0.0.1:6379> set key5 555
      OK
      127.0.0.1:6379> get key5
      "555"
      127.0.0.1:6379> set key5 666
      OK
      127.0.0.1:6379> get key5
      "666"
      127.0.0.1:6379> setnx key6 666
      (integer) 1
      127.0.0.1:6379> get key6
      "666"
      127.0.0.1:6379> setnx key6 777
      (integer) 0
      127.0.0.1:6379> get key6
      "666"
      ```

  * 批量设置/获取：mset/mget key1 value1 key2 value2 key3 value3

  * getset key value：不存在值，返回nil；存在值，先获取原来值，设置新的值



#### 1.2 List



#### 1.3 Set



#### 1.4 Hash



#### 1.5 Zset





### 2 三种特殊数据类型

#### 2.1 geospatial



#### 2.2 hyperloglog



#### 2.3 bitmaps