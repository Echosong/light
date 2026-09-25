# 轻巧之光 lightAdmin

> 面向对象建模驱动的 Java 低代码快速开发框架 —— 写实体类，其余交给框架。

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.x-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-3.5.5-blue)](https://baomidou.com/)
[![Vue](https://img.shields.io/badge/Vue-3.4-42B883?logo=vuedotjs&logoColor=white)](https://vuejs.org/)
[![License](https://img.shields.io/badge/License-MIT-green)](#license)

**轻巧之光（lightAdmin）**是一套前后端分离的通用后台快速开发框架。它把「实体类」作为唯一的建模入口：在实体字段上写注解，框架负责参数校验、统一返回、权限控制、分页、导出、代码生成与前端页面骨架，让 CRUD 类业务从"写十几个文件"变成"写一个实体"。

- 后端仓库（本项目）：<https://github.com/Echosong/light>
- 前端仓库：<https://github.com/Echosong/light-view>

---

## 目录

- [特性一览](#特性一览)
- [技术栈](#技术栈)
- [模块结构](#模块结构)
- [快速开始](#快速开始)
- [工程约定](#工程约定)
- [对象建模注解](#对象建模注解)
- [低代码生成器](#低代码生成器)
- [部署](#部署)
- [参与贡献](#参与贡献)
- [联系我们](#联系我们)
- [License](#license)

---

## 特性一览

| 能力 | 说明 |
| --- | --- |
| 实体驱动建模 | 一个 `@AutoEntity` 实体即完成表结构、DTO、校验、列表/表单配置的声明 |
| 代码自动生成 | 依据实体与字段注解，生成 Controller / Service / Mapper / DTO / Vue 页面全套代码 |
| 统一返回与异常 | `ResultVO` + `ResponseBodyAdvice` + 全局异常拦截，业务代码只关心数据 |
| 参数校验 | 基于 Jakarta Validation，实体上写注解，Controller 加 `@Validated` 即生效 |
| 认证与权限 | Sa-Token 登录态（Token/Cookie），`@Permission` / `@NoPermission` 注解式鉴权 + 角色权限模型 |
| 数据访问混用 | JPA（code first 建表/更新）+ MyBatis-Plus（通用 CRUD）+ MyBatis XML（复杂连表）各取所长 |
| 统一分页 | `PageUtil.getPage` 一行完成 PageHelper 分页、排序拼接与 DTO 转换 |
| 通用能力开箱即用 | 数据字典、系统日志、文件/OSS 上传、Excel 导入导出、接口限流、防重复提交、消息与支付扩展 |

## 技术栈

**后端**

| 组件 | 版本 | 用途 |
| --- | --- | --- |
| JDK | 21 | 运行与编译基线 |
| Spring Boot | 3.5.x | 应用基础框架（`spring-boot-starter-parent`） |
| Spring Data JPA | 随 Boot | code first 建模，`ddl-auto: update` 维护表结构 |
| MyBatis / MyBatis-Plus | 3.5.5 | 通用 CRUD、批量操作、复杂 SQL |
| PageHelper | 2.0.0 | 物理分页（由 `PageUtil` 统一封装） |
| Sa-Token | 1.37.0 | 登录认证、角色/权限校验，会话存 Redis |
| Redis / Redisson | 3.45.1 | 缓存、分布式锁、Sa-Token 会话 |
| Hutool | 5.8.32 | 通用工具类（含 `SmUtil` 国密 SM3 密码摘要） |
| BouncyCastle | 1.74 | 国密算法支撑 |
| EasyExcel / Apache POI | 3.1.1 / 5.2.5 | Excel 导入导出 |
| SpringDoc OpenAPI | 2.8.16 | 接口文档与在线调试（Swagger UI） |
| Enjoy | 5.2.2 | 代码生成器模板引擎 |
| MySQL | 8.0+ | 关系型数据库 |
| 阿里云 OSS SDK | 3.17.4 | 远程文件存储 |

**前端（[light-view](https://github.com/Echosong/light-view)）**：Vue 3.4 + Vite 5 + Ant Design Vue 4.2 + Pinia + Vue Router + ECharts + wangEditor，Node.js 18+。

## 模块结构

```
light
├── Light.Admin          后台管理端：Controller 层 + 启动模块（Web 入口，端口 2034）
├── Light.Api            移动端 / 小程序端：对外接口启动模块（端口 2025）
├── Light.Server         业务逻辑层：Service 接口与实现（业务代码主要落点）
├── Light.Common         框架内核：注解、AOP、统一返回、异常、配置、工具类
├── Light.Entity         数据层：entity 实体（JPA code first） + mapper 接口 + mapper XML
├── Light.Packet         POJO：DTO / QueryDTO / ListDTO 与业务枚举
├── Light.Generator      低代码生成器：读取实体注解生成前后端代码
├── doc/                 数据库脚本与文档（db_light.sql 等）
├── bin/                 部署脚本
├── docker-compose.yml   MySQL + Redis + Admin + Nginx 一键编排
└── Dockerfile           多阶段构建镜像
```

**依赖方向**（上层可依赖下层，反向不可）：

```
Light.Admin / Light.Api  →  Light.Server  →  Light.Entity  →  Light.Common  ←  Light.Packet
                                   ↑
                            Light.Generator（生成期工具，运行时可不用）
```

- `Light.Admin`、`Light.Api` 是**启动模块**，只放 Controller、拦截器与配置，业务逻辑统一写在 `Light.Server`。
- `Light.Common` 不依赖任何业务模块，可被所有模块依赖。

## 快速开始

### 环境要求

- JDK 21
- Maven 3.8+
- MySQL 8.0+
- Redis 6+（Sa-Token 会话与缓存）
- Node.js 18+（前端）

### 1. 初始化数据库

```bash
mysql -uroot -p -e "CREATE DATABASE db_light DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;"
mysql -uroot -p db_light < doc/db_light.sql
mysql -uroot -p db_light < doc/database_optimization.sql   # 可选：索引与优化脚本
```

### 2. 配置数据源与 Redis

`Light.Admin/src/main/resources/application.yml` 默认启用 `pro` 环境，连接信息通过环境变量注入（也可直接修改 `application-pro.yml`）：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/db_light?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: your_password
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      database: 8
file:
  upload-folder: /data/light/upload/   # 本地文件上传目录
```

### 3. 启动后端

```bash
mvn clean install -DskipTests
mvn -pl Light.Admin spring-boot:run        # 管理端，http://localhost:2034
# 移动端/开放接口：
mvn -pl Light.Api   spring-boot:run        # http://localhost:2025
```

接口文档（Swagger UI）：<http://localhost:2034/swagger-ui/index.html>

### 4. 启动前端

```bash
git clone https://github.com/Echosong/light-view.git
cd light-view
npm install

# .env.development 中确认后端地址
# VITE_APP_API_URL='http://localhost:2034/'

npm run dev            # 开发环境，http://localhost:8081（/admin 代理到 2034）
npm run build:prod     # 生产打包，产物在 dist/
```

> 默认账号见 `doc/db_light.sql` 初始化数据（`sys_user` 表），首次登录后请立即修改密码。

## 工程约定

框架把通用能力收敛成固定写法，业务代码请遵循以下约定，避免重复造轮子：

**统一返回**：Controller 直接返回业务对象，`ApiResultAdvice` 自动包装为 `ResultVO`；异常由 `ExceptionHandleAdvice` 统一转成错误码，业务层抛出 `BadRequestException` 即可。

```java
// 业务层只管返回数据，前端统一收到 { code, message, data }
ResultVO.success(userList);
```

**统一分页**：禁止手写 `PageHelper.startPage` 或 `PageImpl`，统一使用 `cn.light.common.util.PageUtil`。

```java
// QueryDTO 需继承 cn.light.common.dto.PageInfo，携带页码与排序
Page<User> page = PageUtil.getPage(this.baseMapper::listPage, queryDTO);
return DtoMapper.convertPage(page, UserListDTO.class);
```

导出等需要全量数据的场景，直接调用 `this.baseMapper.listPage(queryDTO)` 返回 `List`，不走分页。

**参数校验**：实体字段使用 Jakarta Validation 注解（`@NotNull`、`@Size`、`@Pattern`……），Controller 加 `@Validated` 触发校验，无需手写 if 判断。

**常用注解**：

| 注解 | 作用 |
| --- | --- |
| `@Permission(roles/permissions/logical)` | 接口鉴权，支持 AND/OR 组合 |
| `@NoPermission` | 免登录访问（登录、验证码等） |
| `@Log(value, businessType)` | 记录系统操作日志 |
| `@RateLimiter(key, time, count)` | 接口限流 |
| `@NoRepeatSubmit(value)` | 防重复提交（毫秒窗口） |
| `@ExcelAnnotation` | 导出列配置（忽略/宽度） |

**缓存**：缓存统一走 `RedisUtils`，不要在业务代码里直接操作 `RedisTemplate`。

## 对象建模注解

在 `Light.Entity` 的实体类上写注解，即可完成建模与生成规则声明：

```java
@AutoEntity(value = "球队管理", controllerFunction = "listPage,save,delete,find,list")
public class HzTeam extends SysBase {

    @AutoEntityField(value = "球队名称", isKeyName = true, len = 64)
    @NotBlank(message = "球队名称不能为空")
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "所属联赛", htmlType = HtmlTypeEnum.SELECT, source = "/league/all")
    @InQueryDTO
    private Integer leagueId;

    @AutoEntityField(value = "成立时间")
    @InQueryDTO
    private Date createTime;

    @AutoEntityField(value = "备注", htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String remark;
}
```

| 注解 | 位置 | 作用 |
| --- | --- | --- |
| `@AutoEntity` | 类 | 表说明，以及是否生成 dto / controller / service / 列表页 / 表单页 / 详情页 |
| `@AutoEntityField` | 字段 | 中文名、表单组件类型、字典枚举、主名称字段、数据源、长度 |
| `@AutoEntityFieldDefault` | 字段 | 表单默认值 |
| `@InQueryDTO` | 字段 | 该字段进入 QueryDTO，生成列表查询条件（Date 自动拆成 start/end） |
| `@NotinListDTO` | 字段 | 该字段不出现在列表与列表 DTO |
| `@AutoSorted` | 字段 | 排序字段 |
| `@AutoCover` | 类 | 指定生成时可覆盖的文件类型（默认不覆盖已有文件） |

生成的表单/列表组件由 `HtmlTypeEnum` 决定：`INPUT`、`TEXTAREA`、`SELECT`、`RADIO`、`UPLOAD`、`FILE`、`TEXTEDIT`（富文本），前端会自动映射为 Ant Design Vue 组件。

## 低代码生成器

`Light.Generator` 依据实体注解生成**后端全套代码 + 前端 Vue 页面**。

1. 配置生成参数：`Light.Generator/src/main/resources/db-dev.setting`

   ```properties
   url = jdbc:mysql://127.0.0.1:3306/db_light?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8
   user = root
   pass = your_password
   path-base = /path/to/light                # 后端项目根目录
   path-vue  = /path/to/light-view/src/views # 前端页面输出目录
   db-prefix = hz                            # 业务表前缀（Sys 前缀自动归入 system 目录）
   ```

2. 运行 `cn.light.generator.Bootstrap`，按需生成：

   - DTO：`XxxDTO`、`XxxQueryDTO`、`XxxListDTO`
   - Mapper：`XxxMapper.java` + `XxxMapper.xml`
   - Controller、Service 接口与实现
   - 前端页面：`list.vue`（列表）、`add.vue`（新增/编辑）、`info.vue`（详情）

   > 已有文件默认**不覆盖**；需要覆盖时用 `@AutoCover({CodeTypeEnum.ALL})` 显式声明。生成前请先提交或备份代码。

## 部署

**方式一：Docker Compose（推荐）**

```bash
# 首次使用请修改 docker-compose.yml 中的数据库密码
docker compose up -d --build
```

一条命令拉起 MySQL、Redis、Light.Admin（2034）与 Nginx，数据库脚本自动初始化。

**方式二：Jar 包运行**

```bash
mvn clean package -DskipTests
java -jar Light.Admin/target/Light.Admin-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro
```

**方式三：脚本 / 面板部署**

- `bin/` 目录下脚本部署；
- 宝塔面板新建 Spring Boot 项目，选择 JDK 21；
- Jarboot 中新建项目，选择 JDK 21。

> 生产环境务必替换默认数据库密码、关闭 `show-sql`，并将上传目录与日志目录挂载到持久化卷。

## 参与贡献

1. Fork 本仓库并从 `main` 切出特性分支；
2. 提交前请确保 `mvn clean package` 通过，保持既有代码风格与工程约定；
3. 提交信息建议使用 `feat:` / `fix:` / `docs:` 前缀；
4. 通过 Pull Request 提交，并在描述中说明变更动机与影响范围。

问题反馈与需求建议请提到 [Issues](https://github.com/Echosong/light/issues)。

## 联系我们

- QQ 群：571627871
- 作者 QQ：313690636
- 如果【轻巧之光】对你有帮助，欢迎点一个 **Star** ⭐

## License

本项目遵循 [MIT](https://opensource.org/licenses/MIT) 协议，个人与企业均可免费商用。
