# lightAdmin 2.X

【轻巧之光】 基于java spring boot 面向对象建模的低代码快速开发框架

## 一、开发背景 

在Spring Boot生态系统中，虽然存在多种用于加速应用开发的技术栈，但缺乏一种专注于面向对象设计建模的低代码框架。为了填补这一空白，【轻巧之光】应运而生。它不仅简化了开发者的工作流程，还提供了丰富的功能集，使开发者能够更高效地构建高质量的应用程序

## 二、后端技术栈

- **JDK 21**：采用最新版本的Java开发工具包，确保性能优化与安全特性。
- **Spring Boot 3.2.1**：作为微服务架构的基础，提供了一套简洁的配置和强大的功能支持。
- **MyBatis & MyBatis-Plus 3.5.5**：增强版的持久层框架，支持复杂的SQL查询及高效的CRUD操作。
- **Hutool 5.8.14**：一个Java工具包，提供了一系列常用的工具类，简化开发过程。
- **国密 SM2/SM3/SM4 加密算法**：确保数据传输的安全性。
- **Sa-Token 1.37.0**：一个强大的权限认证框架，支持多种认证方式。
- **Redis**：作为缓存中间件，提升系统响应速度。
- **OSS 远程文件存储**：支持阿里云对象存储服务，方便管理应用中的文件资源。
- **MySQL 8.0**：关系型数据库管理系统，提供稳定的数据存储方案。
- **Egzosn 聚合支付**：集成多种支付渠道，简化支付接口接入。
- **ApiFox、Swagger**：支持API接口的调试与文档自动生成。

 **前端相关**

Light框架采用前后端分离。 本项目是“轻巧之光”（lightAdmin）的后端部分，其前端项目的代码仓库位于 [light-view](https://github.com/Echosong/light-view)。


## 三、后端整体架构
```
|--Light.Admin  后台管理端业务controller 层，常用做管理后台的接口 spring boot 项目
|--Light.Api    移动业务端 app 或者小程序 端 业务controller 层，常用做app 或者小程序的接口 spring boot 项目
|--Light.Common  通用代码，公共方法，工具类，常量类，枚举类，s项目
|--Light.Entity 
    |--cache 缓存实体类
    |--entity 实体类 对象建模  code first 形式通过 jpa隐射 生成数据表
    |--mapper mybaits 和 mybatis-plus 的接口类
    |--repository jpa 持久层接口
    |--resources
        |-- mapper  mybatis xml 文件 
|--Light.Generator 低代码自动生成项目
|--Light.Packet pojo 相关转化类 业务枚举
|--Light.Service 业务逻辑层
```

## 四、关键技术点

### 1、参数统一验证 

> entity 类建模的时候可以利用 jakarta.validation.constraints 下的验证实现参数校验
```java
    @AssertFalse - 被注解的元素必须为 false。
    @AssertTrue - 被注解的元素必须为 true。
    @DecimalMax(value) - 被注解的元素必须是一个数字，并且其值不能大于指定的最大值（包括最大值）。
    @DecimalMin(value) - 被注解的元素必须是一个数字，并且其值不能小于指定的最小值（包括最小值）。
    @Digits(integer, fraction) - 被注解的元素必须是一个数字，其整数部分的位数不超过 integer 指定的位数。
    @Future - 被注解的元素必须是一个表示将来日期的时间点。
    @FutureOrPresent - 被注解的元素必须是一个表示现在或将来日期的时间点。
    @Max(value) - 被注解的元素必须是一个数字，其值不能超过指定的最大值（包括最大值）。
    @Min(value) - 被注解的元素必须是一个数字，其值不能低于指定的最小值（包括最小值）。
    @NotNull - 被注解的元素不能是 null。
    @Null - 被注解的元素必须是 null。
    @Past - 被注解的元素必须是一个表示过去的日期的时间点。
    @PastOrPresent - 被注解的元素必须是一个表示现在或过去的日期的时间点。
    @Pattern(regexp) - 被注解的元素必须是一个字符串，且该字符串必须与给定的正则表达式匹配。
    @Size(min, max) - 被注解的元素的大小（长度）必须在指定的范围内。适用于字符串、集合、数组等。
    @NotBlank - 被注解的元素不能是 null 也不能仅由空白字符组成。这个注解只适用于字符串。
    @NotEmpty - 被注解的元素不能是 null 且不能为空。适用于字符串、集合、映射、数组等
```
> controller 层使用 @Validated 注解 或者 @Valid 具体可以参考代码案例


### 2、优雅接口返回

 统一分装返回结构对象，并且做全局拦截获取controller的方法 统一返回
```java

//统一返回结构体
public class ResultVO<T> implements BaseErrorCode {
    public static final int SUCCESS = 200;
    // 返回数据对象内容
    private T data;
    //返回状态码
    private Integer code;
    //返回信息 失败的时候返回错误信息
    private String message;
}


@ControllerAdvice
public class ApiResultAdvice implements ResponseBodyAdvice{
    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       //处理统一返回
    }
}

 //错误拦截捕捉统一返回
@ControllerAdvice
public class ExceptionHandleAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
    }
}

```

### 3、权限认证

1、 sa-token 权限认证 前后端都用token 认证 或者 cookie

2、 权限角色 和 权限模型

```java
    //权限资源实体
    public class SysPermission
    //权限
    public class SysRole
    //权限+角色关系
    public class SysRolePermission
    //用户
    public class SysUser
    //用户+角色关系
    public class SysUserRole
```
3、 权限判断相关注解
```java
    //接口权限组
    @Permission
    //不需要登录就能进权限
    @NoPermission
```

### 4、entity 领域建模

 entity 自定义注解相关作用

```java
//实体类上注解，具体参考各个字段
public @interface AutoEntity
//字段注解 可以设置字段相关处理
public @interface AutoEntityField 
//列表查询使用
public @interface InQueryDTO
// 加此注解的字段不会显示在列表中
public @interface NotinListDTO
```

sysbase 基础实体类 相关定义 审计字段...

### 5、mybaits、mybatis-plus jpa 混用实现多场景数据库操作

jpa 使用主要是jpa 支持code first 模式，每次允许会自动数据库创建相关的表字段。

mybatis-plus 在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生，用来做常用操作批量操作等常规的数据库操作

mybatis 做复杂查询 连表查询 。


### 6、基于领域模型，实现代码自动生成

1、项目配置

resources/db-dev.setting, db-xx.setting 不同环境下配置 可以结合ida 配置环境变量来处理生成的配置

```java
    //定义 实体前缀等相关信息
    public class Const
```
2、运行方式处理
```text
    启动文件在 Bootstrap.java ,每次idea 直接生成
    1、 controller 层生成
    2、 service 层生成
    4、 mapper 层生成
    5、 repository 层生成
    6、 前端代码
```
3、代码生成器

> 生成代码代码时候结合 entity 相关注解，定义生成代码规则


## 五、安装部署

- **导入SQL文件**：将`doc`目录下的SQL文件导入至目标数据库。
- **修改配置文件**：调整`admin`和`api`项目下的YML配置文件，适配实际环境。

### 部署方式

1. **Docker部署**：利用Docker容器化技术，轻松实现应用的快速部署。
2. **纯净脚本部署**：执行`bin`目录下的`run.sh`脚本完成部署。
3. **宝塔部署**：通过宝塔面板新建Spring Boot项目，选择JDK 21版本。
4. **Jarboot部署**：创建新的Jarboot项目，选用JDK 21版本。

## 六、商用

【轻巧之光】遵循MIT开源协议，无论是个人还是企业均可自由使用，无需担心版权问题。



## 七、联系我们

如果您觉得【轻巧之光】对您有所帮助，请给予我们宝贵的支持，例如在GitHub上点个Star。同时，欢迎加入我们的QQ群（571627871），或直接联系QQ：313690636，与我们分享您的想法和建议。


