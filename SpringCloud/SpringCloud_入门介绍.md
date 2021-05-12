## SpringCloud



### 1 SpringCloud与Springboot

* Springboot和Springcloud版本选择

```json
{
	"git": {
		"branch": "f7b959c79f47ef21315779206514bf7f370c4148",
		"commit": {
			"id": "f7b959c",
			"time": "2021-05-11T11:54:31Z"
		}
	},
	"build": {
		"version": "0.0.1-SNAPSHOT",
		"artifact": "start-site",
		"versions": {
			"spring-boot": "2.4.5",
			"initializr": "0.11.0-SNAPSHOT"
		},
		"name": "start.spring.io website",
		"time": "2021-05-11T11:56:16.666Z",
		"group": "io.spring.start"
	},
	"bom-ranges": {
		"azure": {
			"2.2.4": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"3.4.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1"
		},
		"codecentric-spring-boot-admin": {
			"2.2.4": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"2.3.1": "Spring Boot >=2.3.0.M1 and <2.5.0-M1"
		},
		"solace-spring-boot": {
			"1.0.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.1.0": "Spring Boot >=2.3.0.M1 and <2.5.0-M1"
		},
		"solace-spring-cloud": {
			"1.0.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.1.1": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"2.0.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1"
		},
		"spring-cloud": {
			"Hoxton.SR11": "Spring Boot >=2.2.0.RELEASE and <2.3.11.BUILD-SNAPSHOT",
			"Hoxton.BUILD-SNAPSHOT": "Spring Boot >=2.3.11.BUILD-SNAPSHOT and <2.4.0.M1",
			"2020.0.0-M3": "Spring Boot >=2.4.0.M1 and <=2.4.0.M1",
			"2020.0.0-M4": "Spring Boot >=2.4.0.M2 and <=2.4.0-M3",
			"2020.0.0": "Spring Boot >=2.4.0.M4 and <=2.4.0",
			"2020.0.2": "Spring Boot >=2.4.1 and <2.5.0-M1",
			"2020.0.3-SNAPSHOT": "Spring Boot >=2.4.6-SNAPSHOT"
		},
		"spring-cloud-alibaba": {
			"2.2.1.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1"
		},
		"spring-cloud-gcp": {
			"2.0.0": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		},
		"spring-cloud-services": {
			"2.2.6.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.RELEASE",
			"2.3.0.RELEASE": "Spring Boot >=2.3.0.RELEASE and <2.4.0-M1",
			"2.4.1": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		},
		"spring-geode": {
			"1.2.12.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.3.10.RELEASE": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"1.4.5": "Spring Boot >=2.4.0-M1 and <2.5.0-M1",
			"1.5.0-RC1": "Spring Boot >=2.5.0-M1"
		},
		"vaadin": {
			"14.6.0": "Spring Boot >=2.1.0.RELEASE and <2.5.0-M1"
		},
		"wavefront": {
			"2.0.2": "Spring Boot >=2.1.0.RELEASE and <2.4.0-M1",
			"2.1.1": "Spring Boot >=2.4.0-M1 and <2.5.0-M1",
			"2.2.0-RC1": "Spring Boot >=2.5.0-M1"
		}
	},
	"dependency-ranges": {
		"native": {
			"0.9.0": "Spring Boot >=2.4.3 and <2.4.4",
			"0.9.1": "Spring Boot >=2.4.4 and <2.4.5",
			"0.9.2": "Spring Boot >=2.4.5 and <2.5.0-M1",
			"0.10.0-SNAPSHOT": "Spring Boot >=2.5.0-M1 and <2.6.0-M1"
		},
		"okta": {
			"1.4.0": "Spring Boot >=2.2.0.RELEASE and <2.4.0-M1",
			"1.5.1": "Spring Boot >=2.4.0-M1 and <2.4.1",
			"2.0.1": "Spring Boot >=2.4.1 and <2.5.0-M1"
		},
		"mybatis": {
			"2.1.4": "Spring Boot >=2.1.0.RELEASE and <2.5.0-M1"
		},
		"camel": {
			"3.3.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.5.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"3.9.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1"
		},
		"open-service-broker": {
			"3.1.1.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"3.3.0": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		}
	}
}
```



### 2 SpringCloud技术服务架构



#### 2.1 总体架构

* 服务注册与发现
* 服务调用
* 服务熔断
* 负载均衡
* 服务降级
* 服务消息队列
* 配置中心管理
* 服务网关
* 服务监控
* 全链路追踪
* 自动化构建部署
* 服务定时任务调度



#### 2.2 Cloud升级

##### 1 服务注册中心

* ~~Eureka~~
* Zookeeper
* Consul
* **Nacos**



##### 2 服务调用

* Ribbon
* LoadBalancer



##### 3 服务调用2

* ~~Feign~~
* OpenFeign



##### 4 服务降级

* ~~Hystrix~~
* resilience4j
* Sentienl



##### 5 服务网关

* ~~Zuul~~
* gateway



##### 6 服务配置

* ~~Config~~
* Nacos



##### 7 服务总线

* ~~Bus~~
* Nacos