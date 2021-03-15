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



#### 1.2 List



#### 1.3 Set



#### 1.4 Hash



#### 1.5 Zset





### 2 三种特殊数据类型

#### 2.1 geospatial



#### 2.2 hyperloglog



#### 2.3 bitmaps