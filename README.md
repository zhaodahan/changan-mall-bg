#### IDEA 基础
file Encoding UTF-8
Annotation Processor  Enable

#### 软件架构
- SpringCloud：Hoxton.SR1
- SpringBoot：2.2.2RELEASE
- SpringCloud Alibaba：2.1.0.RELEASE
- JAVA：8
- Maven：3.5及以上
- MySQL：5.7及以上
- sleuth+zipkin: 服务链路追踪
- Swagger 接口文档抽取
- nacos :服务注册与发现， 配置中心 ，消息总线bus
- sentinel：服务降级与熔断
- openFeign: 服务调用
- Ribbon：负载均衡
- gateWay: 服务网关

#### 各个模块的作用
Changan-Mall-Common 通用模块。存放通用实体。client 。
Changan-Mall-Order-Core 订单原子模块
Changan-Mall-Order-Edge 订单聚合服务模块

#### P8~P10：支付模块构建与测试-cloud-provider-payment8001
1. 建Module
2. 改pom.xml
3. 写配置文件YML
4. 主启动类
5. 业务类
    - Controller -> Service -> ServiceImpl -> Dao (Mapper) -> Entity
#### P9~P14：消费订单模块与项目重构
 **重点：** 重复性Entity集中提取到cloud-api-commons中，其他子模块可以pom.xml中引入cloud-api-commons，即可获得Entity类
```
<!--引入自己定义的api调用包，可以使用Payment支付Entity-->
<dependency>
    <groupId>com.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

#### sleuth+zipkin: 服务链路追踪
运行 ：java -jar zipkin-server-2.12.9-exec.jar

服务调用方，提供方引入
```
<!--包含了sleuth+zipkin-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```

yml 配置
```
spring:
application:
name: cloud-payment-service
zipkin:
base-url: http://localhost:9411
sleuth:
sampler:
# 抽取比例0-1
probability: 1
```

打开浏览器访问:http:localhost:9411
