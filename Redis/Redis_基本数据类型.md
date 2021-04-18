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

* 在Redis中，List可以实现栈、队列、双向队列。
* 所有list命令都是用l开头

```java
127.0.0.1:6379> keys *
(empty array)
127.0.0.1:6379> lpush list one #将一个或者多个值，插入列表的头部 lpush：左侧 rpush：右侧
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
---------------------------------------------------
127.0.0.1:6379> lrange list 0 -1 #通过区间获取list的值
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> lrange list  0 1
1) "three"
2) "two"
---------------------------------------------------
127.0.0.1:6379> lpop list # lpop左边弹出 rpop右边弹出
"three"
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
3) "zero"
127.0.0.1:6379> rpop list
"zero"
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
---------------------------------------------------
127.0.0.1:6379> lindex list 0 #根据下标查询具体值
"two"
---------------------------------------------------    
127.0.0.1:6379> llen list # 查询列表的长度
(integer) 2
127.0.0.1:6379> flushdb
OK
127.0.0.1:6379> llen list
(integer) 0
---------------------------------------------------
127.0.0.1:6379> lpush list one
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> lpush list three
(integer) 4
127.0.0.1:6379> lpush list three
(integer) 5
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "three"
3) "three"
4) "two"
5) "one"
127.0.0.1:6379> lrem list 2 three # lrem 移除 数量 指定值
(integer) 2
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
---------------------------------------------------
127.0.0.1:6379> flushdb
OK
127.0.0.1:6379> keys *
(empty array)
127.0.0.1:6379> lpush mylist "hello1"
(integer) 1
127.0.0.1:6379> lpush mylist "hello2"
(integer) 2
127.0.0.1:6379> lpush mylist "hello3"
(integer) 3
127.0.0.1:6379> lpush mylist "hello4"
(integer) 4
127.0.0.1:6379> ltrim mylist 1 2 #通过下标截取指定的长度
OK
127.0.0.1:6379> lrange mylist 0 -1
1) "hello3"
2) "hello2"
---------------------------------------------------
rpoplpush：移除列表的最后一个元素，将它移动到新的列表中
127.0.0.1:6379> lrange mylist 0 -1
1) "hello3"
2) "hello2"
127.0.0.1:6379> rpoplpush mylist list2
"hello2"
127.0.0.1:6379> lrange mylist 0 -1
1) "hello3"
127.0.0.1:6379> lrange list2 0 -1
1) "hello2"
---------------------------------------------------
lset：将列表中指定下标的值替换为另外一个值
127.0.0.1:6379> flushdb
OK
127.0.0.1:6379> exists list # 判断列表是否存在。列表不存在就会报错
(integer) 0
127.0.0.1:6379> lset mylist 0 hello
(error) ERR no such key
127.0.0.1:6379> lpush mylist hello
(integer) 1
127.0.0.1:6379> lset mylist 0 world # 存在就更新下标的值，不存在就会报错
OK
127.0.0.1:6379> lrange mylist 0 0
1) "world"
---------------------------------------------------
127.0.0.1:6379> flushdb
OK
127.0.0.1:6379> rpush list hello
(integer) 1
127.0.0.1:6379> rpush list world
(integer) 2
127.0.0.1:6379> linsert list before "world" "redis" #before 在某个单词之前插入某个单词
(integer) 3
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "redis"
3) "world"
```

#### 1.3 Set

* set中的值不能重复
* set的命令以s开头

```
127.0.0.1:6379> sadd myset hello # 添加元素
(integer) 1
127.0.0.1:6379> sadd myset world
(integer) 1
127.0.0.1:6379> sadd myset world
(integer) 0
——————————————————————————————————————————————
127.0.0.1:6379> smembers myset 	#查看set中的元素
1) "world"
2) "hello"
——————————————————————————————————————————————
127.0.0.1:6379> sismember myset hello # 判断元素是否存在 1 存在 0 不存在
(integer) 1
127.0.0.1:6379> sismember myset hello2
(integer) 0
——————————————————————————————————————————————
127.0.0.1:6379> scard myset #获取 set 中值
(integer) 2
——————————————————————————————————————————————
127.0.0.1:6379> srem myset hello #移除set中的指定元素
(integer) 1
127.0.0.1:6379> smembers myset
1) "world"
——————————————————————————————————————————————
127.0.0.1:6379> smembers myset
1) "hello4"
2) "hello3"
3) "hello2"
4) "hello1"
5) "hello5"
127.0.0.1:6379> SRANDMEMBER myset 1 #随机抽取set中的1个值
1) "hello4"
127.0.0.1:6379> SRANDMEMBER myset 1
1) "hello3"
——————————————————————————————————————————————
127.0.0.1:6379> smembers myset
1) "hello3"
2) "hello1"
3) "hello5"
4) "hello4"
5) "hello2"
127.0.0.1:6379> SPOP myset 2 #随机移除元素 
1) "hello1"
2) "hello2"
127.0.0.1:6379> smembers myset
1) "hello3"
2) "hello5"
3) "hello4"
——————————————————————————————————————————————
127.0.0.1:6379> smembers set1
1) "hello3"
2) "hello2"
3) "hello1"
127.0.0.1:6379> smembers set2
1) "world3"
2) "world2"
3) "world1"
127.0.0.1:6379> smove set1 set2 hello1 # 将set1中的 hello1 移动到 set2中
(integer) 1
127.0.0.1:6379> smembers set2
1) "hello1"
2) "world3"
3) "world2"
4) "world1"
127.0.0.1:6379> smembers set1
1) "hello3"
2) "hello2"
——————————————————————————————————————————————
127.0.0.1:6379> sadd set1 a b c d
(integer) 4
127.0.0.1:6379> sadd set2 c d e f
(integer) 4
#交集
127.0.0.1:6379> sinter set1 set2
1) "d"
2) "c"
#并集
127.0.0.1:6379> sunion set1 set2
1) "a"
2) "b"
3) "c"
4) "f"
5) "d"
6) "e"
#差集
127.0.0.1:6379> sdiff set1 set2
1) "a"
2) "b"
```



#### 1.4 Hash

* 存储变更数据：例如user的name、age

```
127.0.0.1:6379> hset hash1 name li #赋值
(integer) 1
127.0.0.1:6379> hget hash1 name #取值
"li"
127.0.0.1:6379> hset hash1 name li name1 zhao name2 qian #多个赋值
(integer) 2
127.0.0.1:6379> hgetall hash1 # 取出所有键值对
1) "name"
2) "li"
3) "name1"
4) "zhao"
5) "name2"
6) "qian"
127.0.0.1:6379> hdel hash1 name2 # 删除指定键值对
(integer) 1
127.0.0.1:6379> 
127.0.0.1:6379> hgetall hash1 
1) "name"
2) "li"
3) "name1"
4) "zhao"
127.0.0.1:6379> hlen hash1 #获取hash的长度
(integer) 2
127.0.0.1:6379> hexists hash1 name2 #判断hash中的key是否存在
(integer) 0
127.0.0.1:6379> hkeys hash1 #获取所有的key
1) "name"
2) "name1"
127.0.0.1:6379> hset hash1 views 1 
(integer) 1
127.0.0.1:6379> hincrby hash1 views 1 #增量+1
(integer) 2
127.0.0.1:6379> hincrby hash1 views 1
(integer) 3
127.0.0.1:6379> hsetnx hash1 sex man #不存在+1，存在不变
(integer) 1
127.0.0.1:6379> hsetnx hash1 sex man
(integer) 0

```



#### 1.5Zset（有序集合）

* 在set的基础上增加 了一个值

```
127.0.0.1:6379> zadd zset1 1 one #增加
(integer) 1
127.0.0.1:6379> zadd zset1 2 two
(integer) 1
127.0.0.1:6379> zrange zset1 0 -1 #查询
1) "one"
2) "two"
127.0.0.1:6379> zadd score 88 xiaoming
(integer) 1
127.0.0.1:6379> zadd score 70 xiaohong 64 xiaogang 90 xiaoli 
(integer) 3
127.0.0.1:6379> zrange score 0 -1 #排序 从低到高
1) "xiaogang"
2) "xiaohong"
3) "xiaoming"
4) "xiaoli"
127.0.0.1:6379> zrangebyscore score -inf +inf #排序 从低到高
1) "xiaogang"
2) "xiaohong"
3) "xiaoming"
4) "xiaoli"
127.0.0.1:6379> zrevrange score 0 -1 # 排序 从高到底
1) "xiaoli"
2) "xiaoming"
3) "xiaohong"
4) "xiaogang"
127.0.0.1:6379> zrevrange score 0 -1 withscores #排序 显示排序数据
1) "xiaoli"
2) "90"
3) "xiaoming"
4) "88"
5) "xiaohong"
6) "70"
7) "xiaogang"
8) "64"
127.0.0.1:6379> zrangebyscore score -inf 80 withscores ## 显示分数在 0 - 80 分之间的人
1) "xiaogang"
2) "64"
3) "xiaohong"
4) "70"
127.0.0.1:6379> zrange zset1 0 -1
1) "one"
2) "two"
127.0.0.1:6379> zrem zset1 one # 根据key删除
(integer) 1
127.0.0.1:6379> zrange zset1 0 -1
1) "two"
127.0.0.1:6379> zcard zset1 # 获取有序集合中的个数
(integer) 1
127.0.0.1:6379> zrevrange score 0 -1 withscores
1) "xiaoli"
2) "90"
3) "xiaoming"
4) "88"
5) "xiaohong"
6) "70"
7) "xiaogang"
8) "64"
127.0.0.1:6379> zcount score 70 89 # 统计符合区间内的值的个数
(integer) 2

```



### 2 三种特殊数据类型

#### 2.1 geospatial 地理位置 -- 底层为zset

* Redis的Geo可以推算出地理位置信息，两地之间的距离，方圆几里的人。
* GEOADD：添加地理位置
  * 两极无法直接添加
  * 参数：key 经度 纬度 名称

```
127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing
(integer) 1
...
127.0.0.1:6379> geoadd china:city 120.16 30.24 hangzhou 108.96 34.26 xian
(integer) 2
```

* GEODIST：返回两个给定位置之间的直线距离。若其中一个不存在，返回空

```
127.0.0.1:6379> geodist china:city beijing hangzhou
"1127337.7813"
127.0.0.1:6379> geodist china:city beijing hangzhou km
"1127.3378"
```

* GEOHASH：讲二维的经纬度转换为一维的字符串。两个字符串越相像，代表位置越近

```
127.0.0.1:6379> geohash china:city shanghai hangzhou
1) "wtw3sj5zbj0"
2) "wtmkn31bfb0"
```

* GEOPOS：获取指定的城市的经度和纬度。

```
127.0.0.1:6379> geopos china:city beijing hangzhou
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
2) 1) "120.1600000262260437"
   2) "30.2400003229490224"

```

* GEORADIUS：以给定的经度和纬度为中心，查询某一半径内的元素

```
127.0.0.1:6379> georadius china:city 110 30 1000 km # key 经度 纬度 半径 单位
1) "chongqing"
2) "xian"
3) "shenzhen"
4) "hangzhou"
127.0.0.1:6379> georadius china:city 110 30 1000 km withdist # 距离
1) 1) "chongqing" #目标
   2) "341.9374"  #距离
2) 1) "xian"
   2) "483.8340"
3) 1) "shenzhen"
   2) "924.6408"
4) 1) "hangzhou"
   2) "977.5143"
127.0.0.1:6379> georadius china:city 110 30 1000 km withcoord  #经度 纬度
1) 1) "chongqing"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
2) 1) "xian"
   2) 1) "108.96000176668167114"
      2) "34.25999964418929977"
3) 1) "shenzhen"
   2) 1) "114.04999762773513794"
      2) "22.5200000879503861"
4) 1) "hangzhou"
   2) 1) "120.1600000262260437"
      2) "30.2400003229490224"
127.0.0.1:6379> georadius china:city 110 30 1000 km count 2 # 查询结果取其中两个结果
1) "chongqing"
2) "xian"
```

* GEORADIUSBYMENBER：通过 key 的查询，查询某一半径内的元素

```
127.0.0.1:6379> georadiusbymember china:city beijing 1000 km
1) "beijing"
2) "xian"
```

* 删除 

```
127.0.0.1:6379> zrange china:city 0 -1
1) "chongqing"
2) "xian"
3) "shenzhen"
4) "hangzhou"
5) "shanghai"
6) "beijing"
127.0.0.1:6379> zrem china:city xian
(integer) 1
127.0.0.1:6379> zrange china:city 0 -1
1) "chongqing"
2) "shenzhen"
3) "hangzhou"
4) "shanghai"
5) "beijing"
```

#### 2.2 hyperloglog：基数统计

* 基数：一个集合中不重复元素的个数
* 传统：用set保存用户的ID，统计set中的元素数量即可。这个方式保存大量的用户ID，会占用大量的内存。
* 优点：占用内存是固定的，特别小。2^64不同的元素，只占12KB内存。
* 0.81%错误率，统计UV任务，可以忽略不计。

```
127.0.0.1:6379> pfadd mykey a b c d e f g # mykey 添加值
(integer) 1
127.0.0.1:6379> pfcount mykey # 统计mykey 存储值的量
(integer) 7
127.0.0.1:6379> pfadd mykey2 e f g h i j k l m 
(integer) 1
127.0.0.1:6379> pfcount mykey2
(integer) 9
127.0.0.1:6379> pfmerge mykey3 mykey mykey2 # 将mykey 和 mykey2 合并为 mykey3 
OK
127.0.0.1:6379> pfcount mykey3 # 统计 mykey3 的存储的值的量
(integer) 13
```



#### 2.3 bitmap

* 位存储
* 统计用户信息，活跃与不活跃、登录与未登录、打卡与未打卡等
* 操作二进制位来进行记录，就只有两种状态：0 和 1

```
# 例子： 一周7天打卡，0代表周日 ，1 代表周一。。。 6代表周六；0代表未打卡，1代表打卡
127.0.0.1:6379> setbit sign 0 0 #赋值
(integer) 0
127.0.0.1:6379> setbit sign 1 1
(integer) 0
127.0.0.1:6379> setbit sign 2 1
(integer) 0
127.0.0.1:6379> setbit sign 3 0
(integer) 0
127.0.0.1:6379> setbit sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 1
(integer) 0
127.0.0.1:6379> setbit sign 6 1
(integer) 0
127.0.0.1:6379> getbit sign 4 	#获取值
(integer) 1
127.0.0.1:6379> getbit sign 3
(integer) 0
127.0.0.1:6379> bitcount sign #  bitcount key [start end] 统计一周打卡天数
(integer) 5

```



