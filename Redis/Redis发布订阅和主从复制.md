## Redis

### 1 Redis发布订阅

通信和队列

* Redis发布订阅（pub/sub）是一种消息通信模式：发布者（pub）发送消息，订阅者（sub）接受消息
* Redis客户端可以订阅任何数量的频道



#### 1.1 发布和订阅

* 消息发布者、频道、消息订阅者

![发布和订阅](\..\images\发布和订阅.jpg)

#### 1.2 Redis命令

* `PSUBSCRIBE pattern [pattern ...] `：订阅一个或多个符合给定模式的频道。
* `PUBSUB subcommand [argument [argument ...]`：查看订阅与发布系统状态。
* `PUBLISH channel message`：将信息发送到指定的频道。--消息发送者
* `PUNSUBSCRIBE [pattern [pattern ...]]`：退订所有给定模式的频道。
* `SUBSCRIBE channel [channel ...]`：订阅给定的一个或多个频道的信息。--订阅内容
* `UNSUBSCRIBE [channel [channel ...]]`：指退订给定的频道。

**订阅者订阅**

```
127.0.0.1:6379> SUBSCRIBE mychannel1
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "mychannel1"
3) (integer) 1
```

**B发布者发布**

```
127.0.0.1:6379> publish mychannel1 helloworld
(integer) 1
127.0.0.1:6379> publish mychannel1 "hi,redis"
(integer) 1
```

**A订阅者收到订阅消息**

```
1) "message"
2) "mychannel1"
3) "helloworld"
1) "message"
2) "mychannel1"
3) "hi,redis"
```



### 1.3 使用场景

* 实时消息系统
* 聊天室、群聊等
* 订阅和关注系统
* 复杂场景使用消息中间件MQ



### 2 主从复制

* 主人（Master）--仆从（salve/follower）

* 从结构上看，单个Redis服务器会发生单点故障，并且一台服务器需要处理所有的请求负载，压力较大
* 从容量上看，单个Redis服务器内存容量有限，就算一台Redis服务器内存容量为256G，也不能将所有内存作用于Redis存储内存，一般来说，单台Redis最大使用内存应该不超过20G
* 电商网站上的商品，一般都是一次上传，无数次浏览，也就是**写少读多**

#### 2.1 概念

* 将一台Redis服务器的数据，复制到其他的Redis服务器上，前者称为主节点（Master），后者称为从节点（Salve），数据复制是单向的，只能由主节点到从节点。Master以写为主，Salve以读为主
* 默认情况下，每台Redis都是主节点
* 一个主节点可以有多个从节点（也可以没有），但是一个从节点只能有一个主节点



#### 2.2 作用

* 数据冗余：主从复制实现了数据热备份，是持久化的一种数据冗余方式
* 故障修复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复；实际上是一种服务的冗余
* 负载均衡：在主从复制的基础上，读写分离，分担服务器负载，尤其是在写少读多的场景下，通过多个从节点分担读负载，可以大大提高Redis服务器的并发量
* 高可用基石：主从复制还是哨兵和集群能够实施的基础



#### 2.3 环境配置

* 只配置从库，不用配置主库(默认为主库)。

```
127.0.0.1:6379> info replication #查看当前库的信息
# Replication
role:master		#角色 master
connected_slaves:0 #没有从机
master_replid:1eb8f7eaf36a867a79c32f6930ef4260bf9c0eb3
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

* 同一个服务器上的配置搭建环境（从机需要修改配置文件的部分）

```
port xxxx -- 进程的端口
daemonize yes -- 后台运行
pidfile /var/run/redis_xxxx.pid -- 记录进程的ID，文件带有锁，可以防止程序的多次启动
logfile "xxxx.log" -- 输出日志文件名称
dbfilename dump_xxxx.rdb --输出持久化文件名称
```

* 配置从机

```
127.0.0.1:6379> SLAVEOF 127.0.0.1 6379 #slaveof host port
OK

127.0.0.1:6379> SLAVEOF no one # 将自己设置为主机(主机宕机后，手动将从机设置为主机)
OK
```

* 真实的主从配置需要在配置文件中配置，使用命令配置是暂时的。

```
#配置文件中
replicaof <masterip> <masterport>
masterauth <master-password>
```

* **主机**负责**写**，**从机**负责**读**，主机的所有写都会被从机保存



#### 2.4 复制原理

* slave启动成功，连接到master后，会发送一个sync同步命令
* master接受到命令后，启动后台的存盘进程，同时收集所有接收到用于修改数据集的命令，在后台进行执行完毕之后，master将传送整个数据文件到slave，并完成一次完全同步。
* **全量复制**：而slave服务在接收到数据库文件后，将其存盘并加载到内存中
* **增量复制**：master继续将新的所有收集到的修改命令依次传给slave，完成同步
* 只要是重新连接到master，一次完全同步（全量复制）就会自动执行



#### 2.5 图示

* 方式一：一主二从

![发布和订阅](\..\images\主从复制1.jpg)

* 方式二：中间的既是主机也是从机



![发布和订阅](\..\images\主从复制2.jpg)



### 3 哨兵模式

