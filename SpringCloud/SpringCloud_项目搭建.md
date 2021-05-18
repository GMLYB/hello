## SpringCloud

* 简单图示

![](..\SpringCloud\images\微服务架构.jpg)



### 1 搭建父工程

* 创建maven项目

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.lyb.springcloud</groupId>
  <artifactId>cloud</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!--总的父工程-->
  <packaging>pom</packaging>

  <!--统一的jar包版本号管理-->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!--子模块继承之后，提供作用：锁定版本，子模块不需要再写version-->
  <dependencyManagement><!--定义规范，但不导入-->
    <dependencies>
      <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-project-info-reports-plugin</artifactId>
        <version>3.0.0</version>
      </dependency>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud 阿里巴巴-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--mysql-->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
        <scope>runtime</scope>
      </dependency>
      <!-- druid-->
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <!--mybatis-->
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <!--junit-->
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <!--log4j-->
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <!--热启动插件-->
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>

```

* maven中**dependencyManagement**和**dependency**的区别
  * **dependencyManagement**
    * 通常会在一个组织或者项目的最顶层的父POM中看到dependencyManagement元素，可以让所有在子项目中引入同一个依赖而不用显示版本号。
    * 优点是更改父项目的版本号，所有子版本都会随之修改，只锁定版本号，不会为子项目引入依赖



### 2 cloud-provider-payment8001（提供者）

#### 2.1 建立模块

创建普通maven模块



#### 2.2 POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud</artifactId>
        <groupId>com.lyb.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

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

    </dependencies>


</project>
```

#### 2.3配置yml

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
```

* 出现MySQL连接爆红，参考解决办法：https://blog.csdn.net/weixin_43885975/article/details/105329422

#### 2.4 主启动

```java
package com.lyb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}

```

#### 2.5 编码

##### 1 创建数据库表语句

```sql
CREATE TABLE `payment`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`serial` VARCHAR(200) DEFAULT '',
	PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

INSERT INTO payment(`serial`) VALUES('test001')
```



##### 2 entities

* 实体类Payment
  * lombok插件安装：https://blog.csdn.net/shmily_lsl/article/details/80689307
  * IDEA官方插件地址：http://plugins.jetbrains.com/plugin/6317-lombok-plugin
  * GitHub上的插件地址：https://github.com/mplushnikov/lombok-intellij-plugin/releases

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

* 实体类CommonReasult

```java
/**
 * 用于返回前端通用的json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonReasult<T> {

    // 200 success
    private Integer code;
    private String message;
    private T data;

    public CommonReasult(Integer code,String message){
        this(code,message,null);
    }
}

```



##### 3 Dao

* PaymentMapper

```java
@Mapper
public interface PaymentMapper {

    //写
    public int create(Payment payment);

    //读
    public Payment getPaymentById(@Param("id")Long id);
}

```

* PaymentMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lyb.cloud.dao.PaymentMapper">
    
    <!--public int create(Payment payment);-->
    <insert id="create" parameterType="Payment" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial) values (#{serial});
    </insert>
    
    <!--public Payment getPaymentById(@Param("id")Long id);-->
    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id = #{id};
    </select>
    <resultMap id="BaseResultMap" type="com.lyb.cloud.entities.Payment">
        <id column="id" property="id" jdbcType="BIGINT" />
        <id column="serial" property="serial" jdbcType="VARCHAR" />
    </resultMap>
    
</mapper>
```



##### 4 Service

* PaymentService

```java
public interface PaymentService {
    //写
    public int create(Payment payment);

    //读
    public Payment getPaymentById(@Param("id")Long id);

}

```

* PaymentServiceImpl

```java
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}

```



##### 5 controller

* PaymentController

```java
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("createPayment：添加Payment返回结果-->" + result);

        if (result > 0){
            return new CommonResult(200,"添加Payment成功",result);
        }else {
            return new CommonResult(-1,"添加Payment失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonReasult QueryPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("QueryPaymentById：查询Payment返回结果-->" + payment);
        if (payment != null){
            return new CommonReasult(200,"查询Payment成功",payment);
        }else {
            return new CommonReasult(-1,"没有查询到对应数据",null);
        }

    }

}
```



##### 6 测试

* 添加：http://localhost:8001/payment/create?serial=test002

```json
{
  "serial":"test004"
}
```

```json
{"code": 200, "message": "添加Payment成功", "data": 1}
```



* 查询：http://localhost:8001/payment/get/2

```json
{
	"code": 200,
	"message": "查询Payment成功",
	"data":{
		"id": 2,
		"serial": "test002"
	}
}
```



### 3 热部署devtools

#### 3.1 依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```



#### 3.2 父工程POM

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```



#### 3.3 开启IDEA自动编译

* Setting --> Build,Exection,Deployment --> Compiler
* 勾选
  * Automatically show first error in editor
  * Display notification on build completion
  * Build project automatically
  * Compile independent modules in parallel



#### 3.4 Update the value

* 快捷键：ctrl + shift + Alt + /
* 选择Registry
* 勾选
  * compiler.automake.allow.when.app.running
  * actionSystem.assertFocusAccessFromEdt
* 重启IDEA



### 4 cloud-consumer-order80（消费者）

#### 4.1 步骤

* 建model
* 改pom
* 写yml
* 主启动
* 业务类



#### 4.2 pom.xml

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
</dependencies>

```



#### 4.3 application.yml

```yaml
server:
  port: 80
```



#### 4.4 主启动

```java
@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
```



#### 4.5 RestTemplate

* 提供了多种快捷访问远程Http服务的方法
* 是一种简单的访问restful服务模块类，是Spring提供的用于访问Rest服务的**客户端模板工具集**
* url：请求地址
* requestMap：请求参数
* Response Bean.class：HTTP响应被转换为对象的类型



#### 4.6 编码

* Payment

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

```

* CommonResult

```java
/**
 * 用于返回前端通用的json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    // 200 success
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}

```

* ApplicationContextConfig

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

* Controller

```java
@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://localhost:8001";


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> cteate(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/ge/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }
}

```



#### 4.7 测试

* 启动PaymentMain8001和OrderMain80

![](..\SpringCloud\images\order_payment1.jpg)

* 结果

![](..\SpringCloud\images\order_payment2.jpg)

* 若出现下面的结果，是因为在cloud-provider-payment8001中的Controller中的create没有给参数加上注解@RequestBody

![](..\SpringCloud\images\order_payment3.jpg)

![](..\SpringCloud\images\order_payment4.jpg)

```java
@PostMapping(value = "/payment/create")
public CommonResult createPayment(@RequestBody Payment payment){
    int result = paymentService.create(payment);
    log.info("createPayment：添加Payment返回结果-->" + result);

    if (result > 0){
        return new CommonResult(200,"添加Payment成功",result);
    }else {
        return new CommonResult(-1,"添加Payment失败",null);
    }
}
```



### 5 工程重构:cloud-api-commons

* 将相似部分提取出来到公共部分



#### 5.1 pom.xml

```xml

<dependencies>

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

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.1.0</version>
    </dependency>

</dependencies>
```



#### 5.2 相似部分

* Payment

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

```

* CommonResult

```java
/**
 * 用于返回前端通用的json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    // 200 success
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}

```

#### 5.3 maven进行clear和install



#### 5.4 改造cloud-consumer-order80和cloud-provider-payment8001

* 删除公共部分
* 在pom中导入公共部分的jar包

```xml
<dependency>
    <groupId>com.lyb.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>

```

