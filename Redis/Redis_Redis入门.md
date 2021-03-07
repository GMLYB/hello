## Redis

### 1 Redis入门

#### 1.1 Redis是什么？

（来源百度百科https://baike.baidu.com/item/Redis/6549233?fr=aladdin）

* Redis（Remote Dictionary Server )，即远程字典服务。
* 是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
* redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。
* 免费和开源，是当下最热门的NoSQL技术之一，被人们称为结构化数据库。



#### 1.2 Redis作用

* 内存存储、持久化（rdb、aof）
* 效率高，开源用于高速缓存
* 发布订阅系统
* 地图信息分析
* 计时器、计数器（浏览量）



#### 1.3 Redis特性

* 多样的数据类型
* 持久化
* 集群
* 事务



#### 1.4 相关资料

* 官网：https://redis.io/
* 中文网：http://www.redis.cn/
* 下载地址：官网下载即可。Window在GitHub上下载（停更很久了，官方不建议在Window上使用）



### 2 Window安装

* 下载安装包。https://github.com/tporadowski/redis/releases Redis-x64-5.0.10.zip

* 下载获得压缩包，并解压到目录
* 双击打开Redis，并用Redis客户端去连接
* 验证。输入ping，出现PONG



### 3 Linux安装

* 官网首页

