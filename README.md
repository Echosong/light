# lightAdmin 2.X

【轻巧之光】 基于java spring boot 面向对象建模的低代码快速开发框架

## 一、开发背景 

spring boot 没有一款基于程序员面向对象设计建模，快速开发类似低代码的框架，有没有合适的？ 那就写个，于是就有了轻巧之光。

## 二、后端技术选项

 - jdk 21
 - spring boot 3.2.1
 - mybatis
 - mybatis-plus 3.5.5
 - hutool 5.8.14 常用工具类
 - 国密 sm2 sm3 sm4 加密
 - sa-token 1.37.0 权限认证
 - redis 缓存中间件
 - oss 远程文件储存
 - mysql8.0 驱动
 - egzosn 聚合支付
 - apifox、swagger 接口调试继承


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


## 四、安装部署

- 导入doc 下 sql 文件到数据库中
- 修改 admin  和 api 项目下 yml 配置文件

### 1、docker 部署


### 2、纯净脚本部署
  执行 运行 bin 下的 run.sh 文件进行部署

### 3、宝塔部署
   网站 > java 项目 > 新建 spring boot 项目 > jdk选21 

### 4、jarboot 部署

  新建 jarboot 项目 jdk版本选择21


## 五、商用

> 任意个人 或者公司使用， 遵循 MIT 最宽泛的开源协议



## 六、联系我们

如果对你有帮助，请点个小星星

qq : 313690636

qq群： 571627871


