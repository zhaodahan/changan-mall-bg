#### IDEA 基础
-file Encoding UTF-8
-Annotation Processor  Enable

#### 软件架构
- SpringCloud：Hoxton.SR3
- SpringBoot：2.2.5.RELEASE
- SpringCloud Alibaba：2.2.1.RELEASE
- JAVA：8
- Maven：3.5及以上
- MySQL：5.7及以上
- sleuth+zipkin: 服务链路追踪
- Swagger 接口文档抽取
- nacos 1.2.1 :服务注册与发现， 配置中心 ，消息总线bus. nacos 默认自带负载均衡，天生集成了ribbon
- sentinel：服务降级与熔断
- openFeign: 服务调用
- Ribbon：负载均衡
- gateWay: 服务网关
- 版本选择参照：https://blog.csdn.net/qq_38637558/article/details/114448690

#### 各个模块的作用
- Changan-Mall-Common 通用模块。存放通用实体。client 。
- Changan-Mall-Order-Core 订单原子模块
- Changan-Mall-Order-Edge 订单聚合服务模块

#### 服务端口约定
- 网关服务，消费侧 端口700X
- 聚合服务，消费侧 端口800X
- 原子服务，消费侧 端口900X


#### P8~P10：支付模块构建与测试-cloud-provider-payment8001
- 1. 建Module
- 2. 改pom.xml
- 3. 写配置文件YML
- 4. 主启动类
- 5. 业务类
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

#### nacos 作为配合中心
- 导入配置
  DEFAULT_GROUP 默认将配置文件打进DEFAULT_GROUP.zip
- 从配置中心读取配置文件的规则
${spring.application.name}-${spring-profile.active}.${spring.cloud.nacos.config.file-extension}

### 生产使用nacos 作为配置中心
官网说明：
https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html
预计需要，1个nginx+3个nacos注册中心+1个mysq
![img.png](img.png)

博客示例：https://cloud.tencent.com/developer/article/1805561

- 配置文件的持久化 (for 生产 Linux)
生产上的配置文件信息需要持久化，目前选择持久化到mysql中
原因：
默认nacos 使用的是嵌入式数据库存储 derby
所以如果使用nacos 集群，数据存储一致性会存在问题
为了解决这个问题，nacos 采用集中式存储方式来支持集群化部署。目前只支持mysql
- ![img_1.png](img_1.png) 


- nacos 的集群配置 (for 生产 Linux)
需要先做持久化配置。 这里主要是通过Nginx代理转发，让我们能通过一个地址访问到nacos上。

配置集群配置文件：nacos的解压目录nacos/的conf目录下，有配置文件cluster.conf
集群模式启动


