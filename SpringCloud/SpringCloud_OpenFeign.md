## SpringCloud

### 1 Feign

* Feign是一个声名式WebService客户端，使用Feign能让编写Web Service客户端更加简单
* 使用方法：定义了一个服务接口，然后再上面添加注解
* Feign支持拔插式的编码器和解码器，SpringCloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。



#### Feign作用

* 编写Java Http客户端更加简单
* 前面使用了Ribbon+RestTemplate，利用了RestTemplate对http请求的封装处理，形成了一套模板化的调用方式。在实际开发中，往往一个接口会被多处调用，所以通常针对每一个微服务进行封装一些客户端类来包装这些以来服务的调用。
* 在Feign中，我们只需要创建一个接口，并使用注解的方式来配置它（以前是Dao接口上标注Mapper注解，现在是在一个微服务接口上标注一个Feign注解），即可完成对服务提供方接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量



### 2 使用

* 新建模块**cloud-consumer-feign-order80**
* POM

```xml
<dependencies>
    <!--openfeign-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <!--公共部分实体类-->
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

* yml

```yaml
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```

* 主启动类

```java
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
```

* 新建PaymentFeignService接口并新增注解FeignClient

```java
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

}
```

* controller

```java
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

}
```

* 测试：http://localhost/consumer/payment/get/1
