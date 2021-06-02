## SpringCloud

### 1 基础概念

#### 1.1 服务治理

* 在传统的rpc远程调用框架中，管理每个服务于服务之间的依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务与服务之间的依赖关系。可以实现服务调用、负载均衡、容错等，实现服务注册与发现。



#### 1.2 服务注册与发现

* **服务注册**，就是将提供某个服务的模块信息(通常是这个服务的ip和端口)注册到1个公共的组件上去（比如: zookeeper\consul）。
* **服务发现**，就是新注册的这个服务模块能够及时的被其他调用者发现。不管是服务新增和服务删减都能实现自动发现。



### 2 Eureka 服务注册于发现

* Eureka采用CS架构，Eureka Server作为服务注册功能的服务器，是属于服务注册中心。系统中的其他微服务，使用Eureka的客户端连接到Eureka Server并维持**心跳连接**，系统人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行
* 在服务注册与发现中，有一个注册中心。当服务器启动的时候，就会把当前服务器的信息以别名的方式注册到注册中心上。另一方（消费者|服务提供者），以该别名的方式去服务注册中心获取实际的服务器通讯地址，在实现本地PRC调用PRC远程框架
* 核心设计思想：服务注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念）。在任何rpc远程框架中，都会有一个注册中心（存放服务地址相关信息）



#### 2.1 Eureka组件

* Eureka Server：提供服务注册服务
* EurekaClient：通过注册中心进行访问。用于简化Eureka Server的交互，客户端同时具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳（默认30s），如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server就会从服务注册表将这个服务节点移除（默认90s）



#### 2.2 单机Eureka构建步骤

* 创建model：cloud-eureka-server7001
* 写pom

```xml
<!--1.X版本和2.X版本比较-->
<!--1.X版本-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
<!--2.X版本-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

```xml
<!--pom的依赖-->
<!--eureka server-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<!--自定义api通用包-->
<dependency>
    <groupId>com.lyb.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!--通用配置-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<!--lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<!--test-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
</dependency>

```

* application.yml

```yaml
server:
  port: 7001


eureka:
  instance:
    hostname: localhost #Eureka服务端实例名称
  client:
    fetch-registry: false #表示自己就是注册中心，不需要进行检索服务
    register-with-eureka: false # 不向注册中心注册自己
    service-url: 
      # 设置与EurekaServer交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

* 主启动类

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}

```

* 效果

![](..\SpringCloud\images\Eureka服务启动.jpg)



##### 1 将cloud-provider-payment8001注册到Eureka Server中

* 添加pom依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

* 添加配置application.yml

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: springcloud
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.lyb.cloud.entities #实体类别名所在的包

eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
      defaultZone: http://localhost:7001/eureka/
```

* 改主启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

* 测试

![](..\SpringCloud\images\将8001提供者注册到EurekaServer中.jpg)



##### 2 将cloud-consumer-order80注册到Eureka Server中

* 添加pom依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

* 配置application.yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
      defaultZone: http://localhost:7001/eureka/
```

* 改主启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}

```

* 测试

![](..\SpringCloud\images\将80消费者注册到EurekaServer中.jpg)



#### 2.3 EurekaServer集群环境构建步骤

* 搭建cloud-eureka-server7002
* pom与7001保持一致

```xml
<dependencies>
    <!--eureka server-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    <!--自定义api通用包-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!--通用配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
    </dependency>
</dependencies>
```

* 修改电脑hosts映射地址:C:\Windows\System32\drivers\etc\hosts

```yaml
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
```

* 修改yml

  * 7001yml

  ```yaml
  server:
    port: 7001
  
  
  eureka:
    instance:
      hostname: eureka7001.com #Eureka服务端实例名称
    client:
      fetch-registry: false #表示自己就是注册中心，不需要进行检索服务
      register-with-eureka: false # 不向注册中心注册自己
      service-url:
        # 设置与EurekaServer交互的地址查询服务和注册服务都需要依赖这个地址
        # defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
         defaultZone: http://eureka7002.com:7002/eureka/
  ```

  * 7002yml

  ```yaml
  server:
    port: 7002
  
  
  eureka:
    instance:
      hostname: eureka7002.com #Eureka服务端实例名称
    client:
      fetch-registry: false #表示自己就是注册中心，不需要进行检索服务
      register-with-eureka: false # 不向注册中心注册自己
      service-url:
        # 设置与EurekaServer交互的地址查询服务和注册服务都需要依赖这个地址
        # defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
         defaultZone: http://eureka7001.com:7001/eureka/
  ```

* 主启动

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}

```

* 测试
  * http://eureka7001.com:7001/
  * http://eureka7002.com:7002/



##### 1 将订单支付服务8001发布到两台Eureka集群配置中

* 修改8001yml

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: springcloud
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.lyb.cloud.entities #实体类别名所在的包

eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
#      defaultZone: http://localhost:7001/eureka
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```

* 修改80yml

```yaml
server:
  port: 80


spring:
  application:
    name: cloud-order-service


eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
#      defaultZone: http://localhost:7001/eureka/
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```



##### 2 启动顺序

* 7001
* 7002
* 8001
* 80



#### 2.4 支付服务provider8002集群配置

* 新建模块：cloud-provider-payment8002

* pom与8001一致

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <!--alibaba-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.10</version>
    </dependency>
    <!--mysql-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!--jdbc-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--公共部分实体类-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

</dependencies>

```

* application.yml

```yaml
server:
  port: 8002

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: springcloud
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.lyb.cloud.entities #实体类别名所在的包

eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
#      defaultZone: http://localhost:7001/eureka
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```

* 主启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}

```

* 业务类与8001一致（别忘记还有资源文件的mapper）

* 修改8001/8002的Controller：添加端口，可以知道是什么数据来自什么端口

  ```java
  @Value("${server.port}")
  private String serverport;
  ```

* 启动顺序
  * 7001
  * 7002
  * 8001
  * 8002
  * 80
* 问题：消费者只能到访问8001端口。访问地址：http://localhost/consumer/payment/get/1

```json
{
    "code":200,
    "message":"查询Payment成功!,serverport = 8001",
    "data":{
        "id":1,
        "serial":"test001"
    }
}
```



#### 2.5 添加负载均衡

* 将消费者80端口的地址改为Eureka上的地址

```java
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
```

* 在ApplicationContextConfig上添加注解@LoadBalanced，实现负载均衡

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

* 访问地址：http://localhost/consumer/payment/get/1
  * 结果：8001/8002轮播出现





#### 2.6 actuator微服务信息完善

* 修改服务名称和显示IP地址

```
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: springcloud
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.lyb.cloud.entities #实体类别名所在的包

eureka:
  client:
    register-with-eureka: true # 向注册中心注册，默认为true
    fetch-registry: true #是否从EurekaServer抓取自己已有的注册信息，默认为true
    service-url:
#      defaultZone: http://localhost:7001/eureka
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
  instance:
    instance-id: payment8001 # 服务名称
    prefer-ip-address: true  # 显示IP地址
```

* 效果

![](..\SpringCloud\images\服务名称和IP地址.jpg)



#### 2.7 服务发现Discovery

获取在Eureka里面注册的微服务的服务信息

* 在8001的Controller中添加DiscoveryClient

```java
@Resource
private DiscoveryClient discoveryClient;

@GetMapping(value = "/payment/discovery")
public Object discovery(){
    List<String> services = discoveryClient.getServices();
    for (String element : services) {
        log.info("PaymentController---> element: " + element);
    }

    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
        log.info(instance.getServiceId() + " : " + instance.getHost() + " : " + instance.getPort() + " : " + instance.getUri());
    }
    return this.discoveryClient;
}
```

* 在启动类中添加注解@EnableDiscoveryClient

```java
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

* 测试

![](..\SpringCloud\images\Discovery.jpg)

![](..\SpringCloud\images\Discovery2.jpg)



#### 2.8 Eureka自我保护

* 保护模式：用于一组客户端和Eureka Server之间存在网络分区场景下的保护，一旦进入保护模式，Eureka Server将会保护其服务注册表中的信息，不再删除服务注册表中的数据。

![](..\SpringCloud\images\Eureka保护模式.jpg)

* 原因：某个时刻某个微服务不能使用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存
* 

* 取消Eureka的自我保护：

  * 7001

  ```properties
  # 关闭自我保护机制
  eureka.server.enable-self-preservation=false
  # 设置距离没有接到心跳的时间
  eureka.server.eviction-interval-timer-in-ms=2000
  ```

  * 8001

  ```properties
  #Eureka客户端向服务端发送心跳的时间间隔，单位为s。默认30s
  eureka.instance.lease-remewal-interval-in-seconds=1
  #Eureka服务端在接收到最后一次心跳后等待的时间，默认为90s,超时将服务剔除
  eureka.instance.lease-expiration-duration-in-seconds=2
  ```

  





### 3 Zookeeper 服务注册与发现

参考文章：https://blog.csdn.net/java_66666/article/details/81015302

* zookeeper，它是一个分布式服务框架，是Apache Hadoop 的一个子项目，它主要是用来解决分布式应用中经常遇到的一些数据管理问题，如：统一命名服务、状态同步服务、集群管理、分布式应用配置项的管理等。
* zookeeper=文件系统+监听通知机制



#### 3.1 Zookeeper的安装

* 官网地址：https://zookeeper.apache.org/releases.html

* window下安装：https://blog.csdn.net/ring300/article/details/80446918

  * 下载，并解压

  * 将conf目录下的zoo_sample.cfg文件，复制一份，重命名为zoo.cfg

  * 修改zoo.cfg配置文件，在安装目录新建data和log文件夹

    * ```
      dataDir=D:/Zookeeper/apache-zookeeper-3.7.0-bin/data
      dataLogDir=D:/Zookeeper/apache-zookeeper-3.7.0-bin/log
      ```

  * 启动：zkServer.cmd

  * 验证是否安装成功：zkCli.cmd，出现`COnnecting localhost 2181` 和  `Welcome to Zookeeper`

* Linux下安装：https://www.cnblogs.com/expiator/p/9853378.html



#### 3.2 创建模块cloud-provider-payment8004

* pom

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--公共部分实体类-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>
    <!--zookeeper客户端-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    </dependency>

</dependencies>

```

* application.yml

```yaml
server:
  port: 8004


#服务别名：将zookeeper注册到服务中心
spring:
  application:
    name: cloud-provider-payment
  cloud:
      zookeeper:
        connect-string: 127.0.0.1:2181
```

* 主启动类

```java
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
```

* controller

```java
@RestController
@Slf4j
public class PaymentController {
    
    @Value("${server.port}")
    private String serverport;
    
    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper:" + serverport + "\t" + UUID.randomUUID().toString();
    }
}
```

* 结果

![](..\SpringCloud\images\8004注册进入zookeeper.jpg)

![](..\SpringCloud\images\8004注册进入zookeeper2.jpg)

![](..\SpringCloud\images\8004注册进入zookeeper3.jpg)

```json
{
  "name": "cloud-provider-payment",
  "id": "2cb0e33e-0789-4757-8e32-c98ca2580a23",
  "address": "9B0Q8QMIWFR62AQ",
  "port": 8004,
  "sslPort": null,
  "payload": {
    "@class": "org.springframework.cloud.zookeeper.discovery.ZookeeperInstance",
    "id": "application-1",
    "name": "cloud-provider-payment",
    "metadata": {}
  },
  "registrationTimeUTC": 1622393834996,
  "serviceType": "DYNAMIC",
  "uriSpec": {
    "parts": [
      {
        "value": "scheme",
        "variable": true
      },
      {
        "value": "://",
        "variable": false
      },
      {
        "value": "address",
        "variable": true
      },
      {
        "value": ":",
        "variable": false
      },
      {
        "value": "port",
        "variable": true
      }
    ]
  }
}
```



#### 3.3  将订单服务注册进入zookeeper

* 创建模块cloud-consumerzk-order80

* pom

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--公共部分实体类-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>
    <!--zookeeper客户端-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    </dependency>

</dependencies>
```

* application.yml

```yaml
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order
  cloud:
      zookeeper:
        connect-string: 127.0.0.1:2181
```

* 主启动类

```java
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class,args);
    }
}
```

* ApplicationContextConfig

```java
@Configuration
public class ApplicationContextConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
}
```

* controller

```java
@RestController
@Slf4j
public class OrderZKController {
    
    public static final String INVOKE_URL = "http://cloud-provider-payment";
    
    @Resource
    private RestTemplate restTemplate;
    
    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk",String.class);
        return result;
    }
    
}

```

* 结果

  * http://localhost/consumer/payment/zk

  ```
  [zk: localhost:2181(CONNECTED) 9] ls /services
  [cloud-consumer-order, cloud-provider-payment]	
  ```







### 4 Consul 服务注册与发现

* 是一套开源的分布式服务发现和配置管理系统，由Go语言开发。
* 官网下载：https://www.consul.io/downloads
* 下载解压之后，出现consul.exe文件即可



#### 4.1 运行consul

* 查看版本：`consul --version`
* 开发模式启动
  * consul agent -dev
  * 访问地址:http://localhost:8500



#### 4.2 服务提供者provider8006

* 建立模块cloud-provider-payment8006

* POM.xml

```xml
<dependencies>
    <!--consul客户端-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
    <!--springboot 整合Web组件-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--日常通用配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--公共部分实体类-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>

</dependencies>

```

* application.yml

```yaml
server:
  port: 8006
  
#服务别名：将consul注册到服务中心
spring:
  application:
    name: cloud-provider-payment
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
  

```

* 主启动类

```java
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class,args);
    }
}

```

* Controller

```java
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @RequestMapping(value = "/payment/consul")
    public String paymentzk(){
        return "springcloud with consul:" + serverport + "\t" + UUID.randomUUID().toString();
    }
}

```

* 结果
  * 访问地址：http://localhost:8006/payment/consul

![](..\SpringCloud\images\8006注册进入consul.jpg)



#### 4.3 服务消费者cloud-consumerconsul-order80

* 新建模块
* pom

```xml
<dependencies>
    <!--consul客户端-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
    <!--springboot 整合Web组件-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--日常通用配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--公共部分实体类-->
    <dependency>
        <groupId>com.lyb.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>

</dependencies>

```

* application.yml

```yaml
server:
  port: 80

#服务别名：将consul注册到服务中心
spring:
  application:
    name: cloud-consumer-order
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}


```

* 主启动类

```java
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class,args);
    }
}

```

* config

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}

```

* controller

```java
@RestController
@Slf4j
public class OrderConsulController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul",String.class);
        return result;
    }

}
```

* 测试
  * 地址：http://localhost/consumer/payment/consul



### 5 三个服务注册中心的异同点