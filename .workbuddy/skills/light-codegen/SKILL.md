---
title: "Light Framework Code Generator"
summary: "替代 Light.Generator 模块的 AI 代码生成 Skill，根据 Entity 注解自动生成 DTO/Mapper/Controller/Service/Vue 视图全套代码"
author: "WorkBuddy"
read_when:
  - 用户要求生成实体类对应的 CRUD 代码
  - 用户要求生成 DTO、Mapper、Controller、Service
  - 用户提到"生成代码"、"codegen"、"代码生成"
  - 用户新增了 Entity 并需要生成配套代码
  - 用户提到 Light.Generator 或 Bootstrap
---

# Light Framework Code Generator

## 概述

本 Skill 替代 `Light.Generator` 模块的 `Bootstrap.java` 启动式代码生成器。
通过解析 Entity 类上的注解（`@AutoEntity`、`@AutoEntityField`、`@InQueryDTO`、`@NotinListDTO`、`@AutoCover`），
自动生成后端 CRUD 全套代码：DTO（3个）、Mapper（Java + XML）、Controller、Service（接口 + 实现），
以及前端 Vue 视图：list.vue（列表页）、add.vue（表单页）、info.vue（详情页）。

**与传统 Generator 的区别**：无需编译运行 Bootstrap.java，直接在对话中指定 Entity 名称即可生成，支持单表/全量生成。

---

## 前置条件

项目必须符合 Light Framework 的标准模块结构：

```
{ROOT_PATH}/
├── Light.Common/      # 注解定义（cn.light.common.anno）
├── Light.Entity/      # 实体类（cn.light.entity.entity）+ Mapper
├── Light.Packet/      # DTO 输出目录（cn.light.packet.dto）
├── Light.Admin/       # Controller 输出目录（cn.light.admin.controller）
├── Light.Server/      # Service 输出目录（cn.light.server.service）
└── Light.Generator/   # 原 Generator（本 Skill 替代它，不需要运行）
```

---

## 第一步：读取项目配置

读取 `Light.Generator/src/main/resources/db-dev.setting`，提取以下关键配置：

| 配置项 | 说明 | 示例值 |
|--------|------|--------|
| `path-base` | 项目根路径（ROOT_PATH） | `D:/项目资料/light框架/framework` |
| `path-vue` | Vue 前端路径（VUE_PATH） | `D:/项目资料/light框架/light-view/src/views` |
| `db-prefix` | 表前缀（TABLE_PREFIX） | `hz` |

如果找不到该文件，从项目根目录的模块结构自动推断：
- `SYS_PATH` = Entity 包名去掉 `.entity.entity`（如 `cn.light`）
- `TABLE_PREFIX` = 询问用户或从实体类名前缀推断
- `ROOT_PATH` = 当前项目根目录

---

## 第二步：定位并解析 Entity

### 2.1 扫描 Entity 文件

Entity 目录：`{ROOT_PATH}/Light.Entity/src/main/java/{SYS_PATH_PATH}/entity/entity/`

> `{SYS_PATH_PATH}` = SYS_PATH 中的 `.` 替换为 `/`，如 `cn.light` → `cn/light`

用 Glob 扫描该目录下所有 `.java` 文件。

### 2.2 解析 Entity 注解

读取 Entity 源码，解析以下注解（详细规则见 `references/annotations.md`）：

**类级别注解：**

```java
@AutoEntity(value = "球队管理", dto = true, controller = true, service = true, 
            controllerFunction = "listPage,save,delete,find,list")
```

- `value` → `tableInfo`（表说明）
- `dto` → 是否生成 DTO（默认 true）
- `controller` → 是否生成 Controller（默认 true）
- `service` → 是否生成 Service（默认 true）

**字段级别注解：**

```java
@AutoEntityField(value = "球队名称", isKeyName = true, htmlType = HtmlTypeEnum.INPUT, 
                 enums = SomeEnum.class, len = 255)
@InQueryDTO          // 该字段出现在 QueryDTO 中
@NotinListDTO        // 该字段不出现在 ListDTO 中
@NotNull(message="") // Jakarta validation 注解
```

### 2.3 提取变量

从 Entity 类中提取以下模板变量：

| 变量名 | 说明 | 生成规则 | 示例 |
|--------|------|---------|------|
| `SYS_PATH` | 项目包路径 | 从包声明推断 | `cn.light` |
| `tableName` | 实体类全名 | `clazz.getSimpleName()` | `HzTeam` |
| `className` | 去前缀类名 | 去掉 `Sys` 或 `TABLE_PREFIX` 前缀 | `Team` |
| `EntityName` | 首字母小写 | `lowerFirst(className)` | `team` |
| `UpEntityName` | 首字母大写 | `upperFirst(className)` | `Team` |
| `UpTableName` | 实体类名 | `upperFirst(tableName)` | `HzTeam` |
| `tableInfo` | 表说明 | `@AutoEntity.value()` | `球队管理` |
| `dbTableName` | 数据库表名 | `toUnderlineCase(tableName).toLowerCase()` | `hz_team` |
| `keyName` | 主名称字段 | `@AutoEntityField(isKeyName=true)` 的字段名首字母大写 | `Name` |
| `isKey` | 是否有主名称 | `keyName` 非空则为 true | `true` |
| `localDate` | 当前时间 | `yyyy-MM-dd HH:mm:ss` 格式 | `2026-07-04 11:00:00` |
| `fields` | 字段列表 | 根据注解过滤后的字段拼接 | 见下方 |
| `import` | 额外导入 | 枚举类等 | `import cn.light.packet.enums.business.*;` |
| `isBigDecimal` | 含 BigDecimal | 字段类型包含 BigDecimal | `true` |
| `sql_where` | XML 查询条件 | 根据 @InQueryDTO 字段生成 | 见下方 |
| `PackageName` | 包名 | 各模块不同 | `cn.light.admin` |

#### className 提取规则（关键）：

```
如果 tableName 以 "Sys" 开头:
    className = tableName 去掉 "Sys" 前缀
    VUE_PATH = VUE_PATH_TEMP + "/system"
否则:
    className = tableName 去掉 TABLE_PREFIX 前缀
    VUE_PATH = VUE_PATH_TEMP + "/business"
```

示例：`HzTeam` + `TABLE_PREFIX=hz` → `className = Team`
示例：`SysUser` → `className = User`

---

## 第三步：生成代码

根据 `@AutoEntity` 的配置，按以下顺序生成。模板内容见 `references/templates/`。

### 3.1 生成 DTO（3个文件）

**触发条件**：`@AutoEntity.dto() == true`

输出目录：`{ROOT_PATH}/Light.Packet/src/main/java/{SYS_PATH_PATH}/packet/dto/{className}/`

| 文件名 | 模板 | 字段过滤规则 |
|--------|------|-------------|
| `{className}DTO.java` | `dto.tpl` | 所有 `@AutoEntityField` 字段（排除 `dto=false`） |
| `{className}QueryDTO.java` | `queryDto.tpl` | 仅 `@InQueryDTO` 标记的字段；Date 类型生成 start/end 两个字段 |
| `{className}ListDTO.java` | `listDto.tpl` | 所有 `@AutoEntityField` 字段（排除 `@NotinListDTO`） |

#### 字段生成规则：

对每个字段生成以下内容（以 DTO 为例）：

```java
// 1. Jakarta validation 注解（如果有）
@NotNull(message = "不能为空")

// 2. @Schema 注解
@Schema(title = "球队名称", description = "枚举说明或notes")

// 3. @ApiModelPropertyEnum（如果字段关联了枚举）
@ApiModelPropertyEnum(SomeEnum.class)

// 4. 字段声明
private String name;
```

#### Date 类型特殊处理（QueryDTO）：

如果 `@InQueryDTO` 字段的类型是 `Date`，生成两个查询字段：

```java
@Schema(title = "创建时间")
private Date startDate;

@Schema(title = "创建时间")
private Date endDate;
```

#### BigDecimal 处理：

如果任何字段类型为 `BigDecimal`，设置 `isBigDecimal = true`，模板会添加：
```java
import java.math.BigDecimal;
```

### 3.2 生成 Mapper（Java + XML）

**触发条件**：Entity 有 `@AutoEntity` 注解（不受 dto 配置控制）

#### Mapper.java

输出路径：`{ROOT_PATH}/Light.Entity/src/main/java/{SYS_PATH_PATH}/entity/mapper/{UpEntityName}Mapper.java`

模板：`mapper.tpl`

生成内容示例：
```java
@Mapper
public interface TeamMapper extends BaseMapper<HzTeam> {
    List<HzTeam> listPage(TeamQueryDTO teamQueryDTO);
}
```

#### Mapper.xml

输出路径：`{ROOT_PATH}/Light.Entity/src/main/resources/mapper/{UpEntityName}Mapper.xml`

模板：`mapperXml.tpl`

`sql_where` 生成规则（根据 `@InQueryDTO` 字段）：

| 字段类型 | 生成的 XML |
|---------|-----------|
| `Date` | `<if test="startDate != null"> and create_time &gt; #{startDate}</if>` + `<if test="endDate != null"> and create_time &lt; #{endDate}</if>` |
| `String` | `<if test="name != null and name != ''"> and name like CONCAT('%',#{name},'%')</if>` |
| 其他 | `<if test="gender != null"> and gender = #{gender}</if>` |

> 字段名转下划线：`StrUtil.toUnderlineCase(fieldName).toLowerCase()`（如 `createTime` → `create_time`）

### 3.3 生成 Controller

**触发条件**：`@AutoEntity.controller() == true`

输出路径：`{ROOT_PATH}/Light.Admin/src/main/java/{SYS_PATH_PATH}/admin/controller/{UpEntityName}Controller.java`

模板：`controller.tpl`

包含方法：`listPage`、`export`、`save`、`find`、`delete`，以及可选的 `getMap`（当 `isKey=true` 时）。

### 3.4 生成 Service（接口 + 实现）

**触发条件**：`@AutoEntity.service() == true`

#### Service 接口

输出路径：`{ROOT_PATH}/Light.Server/src/main/java/{SYS_PATH_PATH}/server/service/{UpEntityName}Service.java`

模板：`implInterface.tpl`

#### Service 实现类

输出路径：`{ROOT_PATH}/Light.Server/src/main/java/{SYS_PATH_PATH}/server/service/impl/{UpEntityName}ServiceImpl.java`

模板：`implService.tpl`

实现类继承 `ServiceImpl<{UpEntityName}Mapper, {UpTableName}>`，包含：
- `listPage` - 分页查询 + DTO 转换
- `export` - Excel 导出
- `save` - 新增/更新
- `delete` - 删除
- `find` - 单条查询
- `getMap`（可选） - 简单下拉数据（当 `isKey=true` 时）

### 3.5 生成 Vue 视图（3个文件）

**触发条件**：`@AutoEntity.viewList() == true`（add.vue + info.vue）和 `@AutoEntity.viewFrom() == true`（list.vue）

输出目录：`{VUE_PATH}/{className}/`

> `VUE_PATH` 从 `db-dev.setting` 的 `path-vue` 读取，再根据 tableName 前缀追加 `/system` 或 `/business`。
> `className` 首字母小写（如 `team`）。

| 文件名 | 模板 | 说明 |
|--------|------|------|
| `list.vue` | `listVue.tpl` | 列表页：查询表单 + 表格 + 分页 + 新增/删除/导出 |
| `add.vue` | `addVue.tpl` | 表单页：Drawer 弹窗 + 表单项 + 验证规则 + 提交 |
| `info.vue` | `infoVue.tpl` | 详情页：Descriptions 展示单条数据 |

#### Vue 视图生成变量

Vue 视图生成需要从 Entity 字段注解中提取额外信息：

| 变量 | 说明 | 来源 |
|------|------|------|
| `htmlType` | 表单组件类型 | `@AutoEntityField.htmlType()` |
| `source` | 数据源路由名 | `@AutoEntityField.source()` |
| `notes` | 字段详细说明 | `@AutoEntityField.notes()` |
| `enums` | 关联枚举类 | `@AutoEntityField.enums()` |
| `isAutoSorted` | 是否排序字段 | `@AutoSorted` 注解 |

#### HtmlTypeEnum 组件类型映射

| 枚举值 | 组件 | 说明 |
|--------|------|------|
| `INPUT` | 按字段类型细分（见下方） | 文本框（默认） |
| `SELECT` | `<select-data>` | 下拉选择（关联数据源） |
| `RADIO` | `<e-switch>` | Switch 开关 |
| `CHECKBOX` | - | 复选框（预留） |
| `TEXTAREA` | `<a-textarea>` | 文本域 |
| `UPLOAD` | `<FileImage>` | 图片上传 |
| `FILE` | `<File>` | 文件上传 |
| `TEXTEDIT` | `<Wangeditor>` | 富文本编辑器 |

> **INPUT 类型按 Java 字段类型细分**：
> - `String` → `<a-input>`
> - `Date` → `<a-date-picker show-time>`
> - `Integer` / `BigDecimal` → `<a-input-number>`
> - 有 `enums` → `<input-enum>`

#### list.vue 生成要点

1. **查询表单**：遍历 `@InQueryDTO` 字段，根据 Java 类型生成查询表单项（String→`<a-input>`，Date→`<a-date-picker>`，BigDecimal/Integer→`<a-input-number>`，枚举→`<input-enum>`，SELECT→`<select-data>`）
2. **表格列**：遍历非 `@NotinListDTO` 字段生成列定义 JSON，末尾追加操作列
3. **查询参数**：分页参数 + 查询字段初始值
4. **import**：根据字段类型收集组件 import

详细规则见 `references/templates/listVue.md`

#### add.vue 生成要点

1. **表单项**：遍历所有 `@AutoEntityField` 字段，根据 htmlType 生成对应组件
2. **验证规则**：从 Jakarta Validation 注解生成 rules 对象
3. **初始数据**：String/Date → `''`，Integer/BigDecimal → `0`；有 `@AutoEntityFieldDefault` 的字段使用注解指定的 `value()`
4. **WangEditor**：TEXTEDIT 字段需要 ref 声明和内容提取

详细规则见 `references/templates/addVue.md`

#### info.vue 生成要点

1. **描述列表**：遍历所有 `@AutoEntityField` 字段生成 descriptions-item
2. **内容展示**：UPLOAD → `<img>`，其他 → `<div v-html>`

详细规则见 `references/templates/infoVue.md`

---

## 第四步：文件覆盖策略

### 默认行为：不覆盖已有文件

如果目标文件已存在，**默认不覆盖**。

### @AutoCover 注解控制覆盖

当 Entity 类上有 `@AutoCover` 注解时，按 `value()` 数组判断是否覆盖：

```java
@AutoCover({CodeTypeEnum.DTO, CodeTypeEnum.CONTROLLER})
```

| CodeTypeEnum 值 | 控制的文件 |
|-----------------|-----------|
| `DTO` | `{className}DTO.java` |
| `QUERY_DTO` | `{className}QueryDTO.java` |
| `LIST_DTO` | `{className}ListDTO.java` |
| `MAPPER` | `{UpEntityName}Mapper.java` |
| `MAPPER_XML` | `{UpEntityName}Mapper.xml` |
| `CONTROLLER` | `{UpEntityName}Controller.java` |
| `SERVICE` | `{UpEntityName}Service.java` + `{UpEntityName}ServiceImpl.java` |
| `LIST_VIEW` | `list.vue` |
| `ADD_VIEW` | `add.vue` |
| `INFO_VIEW` | `info.vue` |
| `ALL` | 所有文件 |

### 覆盖前备份

覆盖前将原文件备份为 `{原文件名}_{yyyyMMddHHmmss}.txt`，然后再写入新内容。

---

## 使用方式

### 单表生成

用户说：
- "给 HzTeam 生成代码"
- "生成 Team 的 CRUD"
- "给 HzTeam 生成 DTO 和 Controller"

Skill 执行流程：
1. 读取 `db-dev.setting` 获取配置
2. 找到 `HzTeam.java` 并解析注解
3. 根据 `@AutoEntity` 配置生成对应文件
4. 报告生成结果

### 全量生成

用户说：
- "生成所有 Entity 的代码"
- "全量代码生成"

Skill 执行流程：
1. 读取配置
2. Glob 扫描 Entity 目录下所有 `.java` 文件
3. 逐个解析注解，跳过没有 `@AutoEntity` 的类
4. 对每个 Entity 执行生成流程
5. 汇总报告

### 指定生成类型

用户说：
- "只给 HzTeam 生成 DTO"
- "给 HzTeam 生成 Mapper 和 Service"

Skill 根据 `@AutoEntity` 的 `dto`/`controller`/`service` 属性，结合用户指定的范围生成。

---

## 参考文件

| 文件 | 说明 |
|------|------|
| `references/annotations.md` | 所有注解的完整定义和解析规则 |
| `references/path-rules.md` | 文件输出路径规则和变量映射 |
| `references/templates/dto.md` | DTO 模板（dto.tpl） |
| `references/templates/queryDto.md` | QueryDTO 模板（queryDto.tpl） |
| `references/templates/listDto.md` | ListDTO 模板（listDto.tpl） |
| `references/templates/mapper.md` | Mapper.java 模板（mapper.tpl） |
| `references/templates/mapperXml.md` | Mapper.xml 模板（mapperXml.tpl） |
| `references/templates/controller.md` | Controller 模板（controller.tpl） |
| `references/templates/implInterface.md` | Service 接口模板（implInterface.tpl） |
| `references/templates/implService.md` | Service 实现模板（implService.tpl） |
| `references/templates/listVue.md` | 列表页模板（listVue.tpl） |
| `references/templates/addVue.md` | 表单页模板（addVue.tpl） |
| `references/templates/infoVue.md` | 详情页模板（infoVue.tpl） |
| `references/templates/data.md` | 数据字典模板（data.tpl） |

---

## 注意事项

1. **包名自适应**：Skill 从 Entity 源码的 `package` 声明推断 `SYS_PATH`，不硬编码 `cn.light`
2. **表前缀自适应**：从 `db-dev.setting` 的 `db-prefix` 读取，不硬编码
3. **SysBase 继承**：Entity 通常继承 `SysBase`，需要合并父类字段（`id`、`createTime` 等）
4. **枚举处理**：`@AutoEntityField.enums()` 非 `BaseEnum.class` 时，生成 `@ApiModelPropertyEnum` 注解和对应 import
5. **Date 类型**：在 QueryDTO 中拆分为 `start{FieldName}` 和 `end{FieldName}` 两个字段
6. **模板变量语法**：原模板使用 Hutool 的 `#(变量名)` 语法，Skill 中直接做字符串替换
7. **条件语法**：`#if(条件) ... #end` 用于条件渲染（如 `isKey`）
8. **Vue 路径推断**：`Sys` 前缀的 Entity → `/system` 子目录，其他 → `/business` 子目录
9. **Vue 字段合并**：Entity 继承 `SysBase` 时，需合并父类字段（非 `SysBase` 时取父类字段）
10. **Vue 列数阈值**：表格列数 >= 10 时，操作列固定 `fixed="right"`
11. **Vue 文件覆盖**：list/add/info 的覆盖策略同样由 `@AutoCover` 的 `LIST_VIEW`/`ADD_VIEW`/`INFO_VIEW` 控制
12. **WangEditor 处理**：TEXTEDIT 类型字段需要额外的 ref 声明和内容提取逻辑
13. **tooltip 生成**：`@AutoEntityField.notes()` 长度 > 4 时，add.vue 表单项添加 `tooltip` 属性
