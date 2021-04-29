## Redis

### 1 Redis.conf

#### 1.1 单位

* 配置文件units对大小写不敏感

```
1k => 1000 bytes
1kb => 1024 bytes
1m => 1000000 bytes
1mb => 1024*1024 bytes
1g => 1000000000 bytes
1gb => 1024*1024*1024 bytes

units are case insensitive so 1GB 1Gb 1gB are all the same.
```



#### 1.2 包含INCLUDES

```
# 可以在这里包含一个或多个其他的配置文件。如果你有一个适用于所有Redis服务器的标准配置模板
# 但也需要一些每个服务器自定义的设置，这个功能将很有用。被包含的配置文件也可以包含其他配置文件，
# 所以需要谨慎的使用这个功能。
#
# 注意“inclue”选项不能被admin或Redis哨兵的"CONFIG REWRITE"命令重写。
# 因为Redis总是使用最后解析的配置行最为配置指令的值, 你最好在这个文件的开头配置includes来
# 避免它在运行时重写配置。
# 如果相反你想用includes的配置覆盖原来的配置，你最好在该文件的最后使用include
#
# include /path/to/local.conf
# include /path/to/other.conf
```



#### 1.3 网络NETWORK

```
bind 127.0.0.1            ##  绑定的ip
protected-mode yes         ##  保护模式
port 6379                    ##  端口设置
```



#### 1.4 通用GENERAL

```
daemonize no #以守护进程方式运行，默认是no，我们需要自己开启为yes!
pidfile /var/run/redis_6379.pid #如果以后台方式运行，我们就需要指定一个pid文件！
日志
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
loglevel notice  #默认生产环境使用notice
logfile "server_log.txt"   #日志的文件名位置
databases 16  #数据库的数量，默认是16个
```



#### 1.5 快照SNAPSHOTTING

* 在规定的时间内，执行了多少次操作，就会持久化到.aof 或者 .rdb
* redis是内存数据库，如果没有持久化，那么数据断电即失！

```
save 900 1     #900秒，有1个key进行操作，就会持久化
save 300 10   #300秒，有10个key进行操作，就会持久化
save 60 10000   #60秒，有10000个key进行操作，就会持久化
我们在使用持久化时，会进行自定义
stop-writes-on-bgsave-error yes  #持久化如果出错，是否还继续工作
rdbcompression yes  #是否压缩rdb文件，需要消耗cup资源
rdbchecksum yes #保存rdb文件的时候，进行错误的校验检查
dir ./   #rdb文件保存的目录
```



#### 1.6 REPLICATION 复制



#### 1.7 SECURITY 安全

```
requirepass设置密码，redis默认是没有密码的
config set requirepass 123456 设置密码
auth 密码 #登录
```



#### 1.8 LIMITS 限制

```
maxclients 10000 #设置能连接上redis的最大客户端数量
maxmemory <bytes> #配置redis的最大内存容量
maxmemory-policy noeviction #内存达到上限的处理策略
#移除一些过期的key
1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 

2、allkeys-lru ： 删除lru算法的key   

3、volatile-random：随机删除即将过期key   

4、allkeys-random：随机删除   

5、volatile-ttl ： 删除即将过期的   

6、noeviction ： 永不过期，返回错误
```



#### 1.9 APPEND ONLY MODE aof配置

```
appendonly no  默认不开启aof  ，因为默认使用rdb的模式，因为rdb大部分场景都可以使用

appendfilename "appendonly.aof" 持久化文件的名字

appendfsync always   每次修改都会同步
appendfsync everysec   每秒执行一次 可能会丢失一秒的数据 （用）
appendfsync no  不同步 这个时候操作系统自己同步，速度最快，一般不用
```



### 2 Redis持久化

* Redis是内存数据库，如果不能将内存中的数据库状态保存到磁盘，那么当服务器进程退出，服务器中的数据库状态也会消失，所以Redis提供了持久化功能。
* 参考文章地址：https://www.jianshu.com/p/1d9ab6bc0835



#### 2.1 RDB(Redis DataBase)--快照

在指定的时间间隔内生成数据集的时间点快照（point-in-time snapshot）

![img](https://static.oschina.net/uploads/space/2017/0628/115308_FLkD_2286631.png)

* 流程
  * redis根据配置自己尝试去生成rdb快照文件
  * fork一个子进程出来
  * 子进程尝试将数据dump到临时的rdb快照文件中
  * 完成rdb快照文件的生成之后，就替换之前的旧的快照文件dump.rdb，每次生成一个新的快照，都会覆盖之前的老快照
* 触发机制
  * save的规则满足的情况下，会自动触发rdb规则
  * 执行flashall命令
  * 退出Redis

* RDB 的优点
  * **RDB 是一个非常紧凑（compact）的文件**，它保存了 Redis 在某个时间点上的数据集。 这种文件非常适合用于进行备份： 比如说，你可以在最近的 24 小时内，每小时备份一次 RDB 文件，并且在每个月的每一天，也备份一个 RDB 文件。 这样的话，即使遇上问题，也可以随时将数据集还原到不同的版本。
  * **RDB 非常适用于灾难恢复（disaster recovery）**：它只有一个文件，并且内容都非常紧凑，可以（在加密后）将它传送到别的数据中心，或者亚马逊 S3 中。
  * **RDB 可以最大化 Redis 的性能**：父进程在保存 RDB 文件时唯一要做的就是 fork 出一个子进程，然后这个子进程就会处理接下来的所有保存工作，父进程无须执行任何磁盘 I/O 操作。
  * **RDB 在恢复大数据集时的速度比 AOF 的恢复速度要快。**
* RDB的缺点
  * RDB 文件需要保存整个数据集的状态， 所以它并不是一个轻松的操作。 因此你可能会至少 5 分钟才保存一次 RDB 文件。 在这种情况下， 一旦发生故障停机， 你就可能会丢失好几分钟的数据。
  * 每次保存 RDB 的时候，Redis 都要 fork() 出一个子进程，并由子进程来进行实际的持久化工作。 在数据集比较庞大时， fork() 可能会非常耗时，造成服务器在某某毫秒内停止处理客户端。



#### 2.2 AOF（Append Only File）--日志

> appendonly on #默认是关闭的

![img](https://static.oschina.net/uploads/space/2017/0628/134509_RgwT_2286631.png)

* 流程
  * redis fork一个子进程
  * 子进程基于当前内存中的数据，构建日志，开始往一个新的临时的AOF文件中写入日志
  * redis主进程，接收到client新的写操作之后，在内存中写入日志，同时新的日志也继续写入旧的AOF文件
  * 子进程写完新的日志文件之后，redis主进程将内存中的新日志再次追加到新的AOF文件
  * 用新的日志文件替换掉旧的日志文件

* AOF的优点
  * AOF 的默认策略为每秒钟 fsync 一次，在这种配置下，Redis 仍然可以保持良好的性能，并且就算发生故障停机，也最多只会丢失一秒钟的数据
  * AOF 文件是一个只进行追加操作的日志文件（append only log）， 因此对 AOF 文件的写入不需要进行 seek ， 即使日志因为某些原因而包含了未写入完整的命令（比如写入时磁盘已满，写入中途停机，等等）， **redis-check-aof** 工具也可以轻易地修复这种问题。
  * Redis 可以在 AOF 文件体积变得过大时，自动地在后台对 AOF 进行重写： 重写后的新 AOF 文件包含了恢复当前数据集所需的最小命令集合。 整个重写操作是绝对安全的，因为 Redis 在创建新 AOF 文件的过程中，会继续将命令追加到现有的 AOF 文件里面，即使重写过程中发生停机，现有的 AOF 文件也不会丢失。 而一旦新 AOF 文件创建完毕，Redis 就会从旧 AOF 文件切换到新 AOF 文件，并开始对新 AOF 文件进行追加操作。
  * AOF 文件有序地保存了对数据库执行的所有写入操作， 这些写入操作以 Redis 协议的格式保存， 因此 AOF 文件的内容非常容易被人读懂， 对文件进行分析（parse）也很轻松。
* AOF 的缺点
  * AOF 文件的体积通常要大于 RDB 文件的体积
  * 根据所使用的 fsync 策略，AOF 的速度可能会慢于 RDB 



#### 2.3 总结

```
RDB
优点：
	1.体积更小：相同的数据量rdb数据比aof的小，因为rdb是紧凑型文件
	2.恢复更快：因为rdb是数据的快照，基本上就是数据的复制，不用重新读取再写入内存
	3.性能更高:父进程在保存rdb时候只需要fork一个子进程，无需父进程的进行其他io操作，也保证了服务器的性能。

缺点：
	1.故障丢失:因为rdb是全量的，我们一般是使用shell脚本实现30分钟或者1小时或者每天对redis进行rdb备份，（注，也可以是用自带的策略），但是最少也要5分钟进行一次的备份，所以当服务死掉后，最少也要丢失5分钟的数据。
	2.耐久性差:相对aof的异步策略来说，因为rdb的复制是全量的，即使是fork的子进程来进行备份，当数据量很大的时候对磁盘的消耗也是不可忽视的，尤其在访问量很高的时候，fork的时间也会延长，导致cpu吃紧，耐久性相对较差。
```

```
aof
优点：
	1.数据保证：我们可以设置fsync策略，一般默认是everysec，也可以设置每次写入追加，所以即使服务死掉了，咱们也最多丢失一秒数据
	2.自动缩小：当aof文件大小到达一定程度的时候，后台会自动的去执行aof重写，此过程不会影响主进程，重写完成后，新的写入将会写到新的aof中，旧的就会被删除掉。但是此条如果拿出来对比rdb的话还是没有必要算成优点，只是官网显示成优点而已。

缺点：
	1.性能相对较差：它的操作模式决定了它会对redis的性能有所损耗
	2.体积相对更大：尽管是将aof文件重写了，但是毕竟是操作过程和操作结果仍然有很大的差别，体积也毋庸置疑的更大。
	3.恢复速度更慢：
```







