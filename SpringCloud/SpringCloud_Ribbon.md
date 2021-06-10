## SpringCloud

### 1 负载均衡

#### 2.1 LB负载均衡

* 用户的请求平摊的分配到多个服务上，达到系统的HA（高可用）
* 常见的负载均衡软件Nginx、LVS、硬件F5等



#### 2.2 集中式LB

> 在服务的消费方和提供方之间使用独立的LB设施（硬件：F5等；软件：Nginx等），由该设施负责把访问请求通过某种策略转发至服务的提供方



#### 2.3 进程内LB

> 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择一个合适的服务器。
>
> Ribbon属于进程内LB，它只是一个类库，集成与消费方进程，消费方通过它来获取到服务提供方的地址。



#### 2.4 Ribbon本地负载均衡客户端和Nginx服务端负载均衡的区别

* Nginx是服务器负载均衡，客户端会将所有请求都交给Nginx，然后由Nginx实现请求转发，负载均衡是由服务端实现的
* Ribbon是本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册服务信息列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。



### 2 Ribbon

* 是基于Netflix Ribbon实现的一套客户端负载均衡工具
* 负载均衡+RestTemplate调用
* Ribbon在工作时的步骤：
  * 选择EurekaServer，优先选择同一个区域内负载较小的server
  * 根据用户指定的策略，从server取到服务注册列表的中选择一个地址

![](..\SpringCloud\images\Ribbon负载均衡.jpg)

* 相关依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```



### 3 RestTemplate

#### 3.1 getForObject方法/getForEntity方法

* Object：返回对象为响应体中数据转换的对象，json
* Entity：返回对象为ResponseEntity。包含了响应的一些重要信息，如响应头、响应状态码、响应体等

```java
//在cloud-consumer-order80中的Controller中添加下面一段
//访问地址：https://localhost/consumer/payment/getForEntity/1
@GetMapping("/consumer/payment/getForEntity/{id}")
public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
    ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    if (entity.getStatusCode().is2xxSuccessful()){
        return entity.getBody();
    }else {
        return new CommonResult<>(444,"操作失败");
    }
}
```

