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

 

### 4 IRule

根据特定算法从服务列表中选取一个要访问的服务

```java
public interface IRule{
    /*
     * choose one alive server from lb.allServers or
     * lb.upServers according to key
     * 
     * @return choosen Server object. NULL is returned if none
     *  server is available 
     */

    public Server choose(Object key);
    
    public void setLoadBalancer(ILoadBalancer lb);
    
    public ILoadBalancer getLoadBalancer();    
}
```

* 7种特定算法(IRule的实现类)

  * **com.netflix.loadbalancer.RoundRobinRule** 轮询
  * **com.netflix.loadbalancer.RandomRule** 随机
  * **com.netflix.loadbalancer.RetryRule** 先按照轮询策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
  * **WeightedResponseTimeRule** 对轮询策略的拓展，响应速度越快的实例选择权重越大，越容易被选择
  * **BestAvailableRule** 过滤掉由于多次访问故障而处于断路由器跳闸状态的服务，然后选择一个并发量最小的服务
  * **AvailabilityFilteringRule** 过滤掉故障实例，选择并发较小的实例
  * **ZoneAvoidanceRule** 默认规则，复合判断server所在区域的性能和server的可用性选择服务器

* 替换

  * 新建包名：com.lyb.myrule（规则不能放在主启动类及其以下的路径，因此需要新建一个包）
  * 新建Java：MySelfRule

  ```java
  @Configuration
  public class MySelfRule {
      
      @Bean
      public IRule myRule(){
          return new RandomRule();//定义轮询规则为随机
      }
  }
  ```

  * 修改controller

  ```java
  @SpringBootApplication
  @EnableEurekaClient
  @RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
  public class OrderMain80 {
      public static void main(String[] args) {
          SpringApplication.run(OrderMain80.class,args);
      }
  }
  ```

* 测试：`http://localhost/consumer/payment/get/1`



### 5 负载均衡算法原理

#### 5.1 原理

* 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标。每次服务器重启后rest接口计数从1开始

> List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
>
> 如：
>
> ​	List[0] instances = 127.0.0.1:8002
>
> ​	List[1] instances = 127.0.0.1:8001
>
> 8001 + 8001组合为集群，集群总数为2，轮询算法原理 
>
> 总请求数为1时：1 % 2 = 1，对应下标为1，则获取127.0.0.1:8001
>
> 总请求数为2时：2 % 2 = 0，对应下标为0，则获取127.0.0.1:8002
>
> 总请求数为3时：3 % 2 = 1，对应下标为1，则获取127.0.0.1:8001
>
> 总请求数为4时：4 % 2 = 0，对应下标为0，则获取127.0.0.1:8002
>
> ..........



