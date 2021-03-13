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

* 官网首页 https://redis.io/download
* 通过远程工具，把Redis压缩包放入Linux服务器中。
* 找到文件位置，输入`tar zxvf redis-x.x.x.tar.gz`解压
* ![image-20210313231542074](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210313231542074.png)
* 解压之后，进入解压后的目录，进行输入`make`编译
* 编译完成之后，进入`/src`目录，输入`make install`进行安装

##### Redis部署

* 修改配置文件，让Redis后台启动。`redis.conf` 中的 daemonize改为yes
* 启动：`redis-server configPath`
* 测试是否启动成功：`redis-cli ping`
* 测试是否可用：
  * `redis-cli -p 6379`
  * `set testkey testvalue` -- `OK`
  * `get testkey` -- `testvalue`
* 关闭redis：`redis-cli shutdown`
* 卸载redis：
  * 查看Redis的监听：`ps aux|grep redis`
    * 关闭Redis：`redis-cli shutdown` 或 `kill -9 pid`（直接杀死线程）
  * 删除文件夹 rm -rf redis(redis为相关目录)

##### Redis设置密码

* **永久性设置**。即在配置里面写入密码（推荐）
  * 在`redis`的`redis.conf`配置中找到`#requirepass foobared` ，去掉‘`#`’ 
  * `foobared` 改成要设置的密码，如：`requirepass test123`。这样需要重启下`redis`的服务，就可设置成功。
* **设置临时新的密码，不需要重启，重启后临时密码无效。**
  * 进入`redis-cli`：`redis-cli -p 6379`
  * 设置密码：`config set requirepass test123`
  * 查询密码：`config get requirepass`
  * 密码验证：`auth test123`

##### Redis修改端口号

* 在在`redis`的`redis.conf`配置中找到`port 6379`，改成想要的端口，如：`6378`
* 访问：`redis-cli -p 6378` 

