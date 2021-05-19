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





### 3 Zookeeper 服务注册与发现





### 4 Consul 服务注册与发现



