# light
【轻巧之光】 基于java spring boot 面向对象建模低的代码快速开发框架

## 开发背景 

spring boot 没有一款基于程序员面向对象设计建模，快速开发类似低代码的框架，没有合适的？ 那就写个，于是就有了轻巧之光。

# 配置步骤
1、 打开mysql 新建数据库  执行 sql 文件夹 db_light.sql 文件

2、 修改 application.yml 文件 配置数据库，前端路径，系统包 等等信息

3、 执行 test下 LightInitTest.autoAppliaction() 测试函数 自动化生成相关代码

4、 运行spring boot  启动main

5、 前端 文件夹 admin-ui  配置好 vue.config.js 相关的代理（模式直接可以使用）
```javascript
    npm run serve 运行
``` 
 账号: admin  密码: 123456

6、 新增功能新增 entity 文件 完事 运行第二步生成相关代码，在运行 3,4 步骤

7、 配置好后台权限 使菜单能正确显示出来

## 开发代码结构规范（简单、实用、高效、规范）

1. Controller 里面可以处理数据一般操作 Repostiory 操作常规简单数据逻辑
2. Service 操作通用复杂的业务处理逻辑
3. 数据库操作只用JPA 做到SQL 放JPA接口
4. DTO 处理查询，也前端特殊显示模型，大部分业务模型通用Entity 里面实体


### Controller 层规范
1. 可以做简单业务操作，这个地方跟市面上流行的分包思路可能不一样， 本人比较细化简单、直接。 简单的操作而且是比较独立的，没有必要形式主义，弄个service层
2. 拿到请求，做校验 主要 用 hibernate-validator，做简单业务操作。
3. 复杂 或者 共用的 业务操作下沉给service解决
4. 所有Controller 继承父类 BaseController ， 父类处理 Http request 相关的操作，比如获取 客户端ip ...

### service 层规范
1、处理 复杂 业务操作
2、谨慎处理 @Transactional 事务注解的使用，不要简单对 service 的方法添加个 @Transactional 注解就觉得万事大吉了。应当合并对数据库的操作，尽量减少添加了@Transactional方法内的业务逻辑。
@Transactional 注解内的 rollbackFor 值必须使用异常的基类 Throwable.class

3、需要注意的是：注解 @Transactional 事务在类的内部方法调用是不会生效的

> Spring采用动态代理(AOP)实现对bean的管理和切片，它为我们的每个class生成一个代理对象。只有在代理对象之间进行调用时，可以触发切面逻辑。而在同一个class中，方法A调用方法B，调用的是原对象的方法，而不通过代理对象。所以Spring无法拦截到这次调用，也就无法通过注解保证事务了。简单来说，在同一个类中的方法调用，不会被方法拦截器拦截到，因此事务不会起作用。

4、 所以这里 Controller 做一些简单的验证，减少事务粒度， 事务在 service 开启调用

### repository 层

1、 主要jpa 操作接口, 常规jpa 操作满足 80% 业务需要

2、 复杂的sql 直接 用 @Query(nativeQuery = true, value = "***")

3、 一些连表 需要返回的 实体，不在 entity的， 通过返回 List<Map<String, Object>>, 在通过 BeanUtil 转换为相应的DTO list

###  代码工具都是借助此组件 hutool


### 数据库

1、 采用了jpa codeFirst 模式， 所有的数据表都通过 entity 进行自动生成

2、 hibernate 配置一定要注意 为 update
```yaml
 hibernate:
   ddl-auto: update
```

### 权限认证框架 sa-token

1、 Sa-Token 是一个轻量级 Java 权限认证框架，主要解决：登录认证、权限认证、Session会话、单点登录、OAuth2.0、微服务网关鉴权 等一系列权限相关问题

2、 开启了redis 存储，方便扩展分布式


### 启用  缓存

```
    @Cacheable(value="**") 
    @CacheEvict(value = "**", allEntries = true)
``` 

```yaml
spring:
    cache:
      type: redis
```

### Swagger枚举类型

实现了一个swagger插件ModelPropertyBuilderPlugin插件：SmartSwaggerApiModelEnumPlugin,在插件中使用@ApiModelPropertyEnum注解，这样在swagger文档中就可以很好的显示了


### 常用验证码

Bean Validation 中内置的 constraint     
@Null   被注释的元素必须为 null     
@NotNull    被注释的元素必须不为 null     
@AssertTrue     被注释的元素必须为 true     
@AssertFalse    被注释的元素必须为 false     
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内     
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内     
@Past   被注释的元素必须是一个过去的日期     
@Future     被注释的元素必须是一个将来的日期     
@Pattern(regex=,flag=)  被注释的元素必须符合指定得正则表达式     
Hibernate Validator 附加的 constraint     
@NotBlank(message =)   验证字符串非null，且长度必须大于0     
@Email  被注释的元素必须是电子邮箱地址     
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内     
@NotEmpty   被注释的字符串的必须非空     
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内

## 开发流程

 开发人员拿到需求以后，针对需求提炼成功对面向对象实体模型只需要新增 entity 文件夹下的实体。

> 有以几个注解配合来生成业务

1、AutoEntity 作用在实体类上，用来确定实体是否需要生成对应的 controller  前端 list, 表单

2、AutoEntityField 各种字段名称，验证码 等等

3、InQueryDTO 是否做完前端列表 头部查询使用字段

4、NotinListDTO 此字段是否在列表中使用

## 效果


![运行效果](https://github.com/Echosong/light/blob/main/web.png?raw=true)

