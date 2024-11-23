# lightAdmin

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

### 2、优雅接口返回

### 3、权限认证

### 4、entity 领域建模

### 5、mybaits、mybatis-plus jpa 混用实现多场景数据库操作

### 6、基于领域模型，实现代码自动生成

## 安装部署

### 1、docker 部署
### 2、纯净脚本部署
### 3、宝塔部署
### 4、jarboot 部署


## 商用

> 任意个人 或者公司使用， 遵循 MIT 最宽泛的开源协议



## 联系我们

如果对你有帮助，请点个小星星

qq : 313690636

qq群： 571627871


