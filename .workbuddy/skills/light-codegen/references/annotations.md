# 注解定义与解析规则

## 类级别注解

### @AutoEntity

**包路径**：`cn.light.common.anno.AutoEntity`

标记一个 Entity 需要自动生成代码。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntity {
    String value() default "";                    // 表说明（如 "球队管理"）
    boolean dto() default true;                   // 是否生成 DTO
    boolean viewList() default true;              // 是否生成前端 add.vue
    boolean viewFrom() default true;              // 是否生成前端 list.vue
    boolean viewInfo() default true;              // 是否生成前端 info.vue
    boolean controller() default true;            // 是否生成 Controller
    boolean service() default true;               // 是否生成 Service
    String controllerFunction() default "listPage,save,delete,find,list"; // Controller 方法列表
}
```

**解析规则**：
- 读取 `value` → `tableInfo` 变量
- `dto=true` → 生成 3 个 DTO 文件
- `viewFrom=true` → 生成 list.vue（列表页）
- `viewList=true` → 生成 add.vue（表单页）+ info.vue（详情页）
- `controller=true` → 生成 Controller
- `service=true` → 生成 Service 接口 + 实现
- 没有 `@AutoEntity` 的 Entity 跳过不生成

### @AutoCover

**包路径**：`cn.light.common.anno.AutoCover`

标记代码生成时是否覆盖已有文件。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoCover {
    CodeTypeEnum[] value() default {CodeTypeEnum.NULL};
}
```

**CodeTypeEnum 枚举值**：

| 枚举值 | 对应文件 |
|--------|---------|
| `NULL` | 不覆盖任何文件（默认） |
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

**解析规则**：
- 无 `@AutoCover` 注解 → 文件存在则跳过
- `@AutoCover` 注解的 `value` 包含目标类型或 `ALL` → 覆盖（先备份）
- 覆盖前备份原文件为 `{原文件名}_{yyyyMMddHHmmss}.txt`

---

## 字段级别注解

### @AutoEntityField

**包路径**：`cn.light.common.anno.AutoEntityField`

描述实体字段的元数据，是代码生成的核心数据源。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntityField {
    String value() default "";                     // 字段中文名（如 "球队名称"）
    String notes() default "";                     // 详细说明（长度>4时生成 tooltip）
    boolean dto() default true;                    // 是否生成到 DTO
    String source() default "";                    // 数据源 routeName（SELECT 组件用）
    int len() default 255;                         // 字符长度
    boolean isKeyName() default false;             // 是否为主名称（getMap 使用）
    HtmlTypeEnum htmlType() default HtmlTypeEnum.INPUT; // 表单类型
    Class<? extends BaseEnum> enums() default BaseEnum.class; // 关联枚举类
}
```

**HtmlTypeEnum 枚举值**：

| 枚举值 | 代码 | Vue 组件 | 说明 |
|--------|------|---------|------|
| `INPUT` | 0 | `<a-input>` / `<a-date-picker>` / `<input-enum>` | 文本框（默认） |
| `SELECT` | 1 | `<select-data>` | 下拉选择（关联数据源 source） |
| `RADIO` | 2 | `<e-switch>` | Switch 开关 |
| `CHECKBOX` | 3 | - | 复选框（预留） |
| `UPLOAD` | 4 | `<FileImage>` | 图片上传 |
| `TEXTAREA` | 5 | `<a-textarea>` | 文本域 |
| `FILE` | 6 | `<File>` | 文件上传 |
| `TEXTEDIT` | 7 | `<Wangeditor>` | 富文本编辑器 |
```

**解析规则**：
- `value` → `@Schema(title = "...")`
- `notes` 非空 → 追加 `description = "..."`
- `enums` 非 `BaseEnum.class` → 生成 `@ApiModelPropertyEnum({EnumName}.class)` + import
- `isKeyName=true` → 设置 `keyName = upperFirst(fieldName)`，`isKey = true`
- `htmlType` → Vue 视图表单组件类型（INPUT/SELECT/RADIO/TEXTAREA/UPLOAD/FILE/TEXTEDIT）

### @InQueryDTO

**包路径**：`cn.light.common.anno.InQueryDTO`

标记字段出现在 QueryDTO 中（作为查询条件）。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface InQueryDTO {}
```

**解析规则**：
- 标记式注解（无属性）
- 在 QueryDTO 生成时，只包含带此注解的字段
- 在 Mapper.xml 中，只对这些字段生成 `<if>` 查询条件
- Date 类型字段会生成 `start{FieldName}` 和 `end{FieldName}` 两个字段

### @NotinListDTO

**包路径**：`cn.light.common.anno.NotinListDTO`

标记字段不出现在 ListDTO 中（列表页面不展示）。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface NotinListDTO {}
```

**解析规则**：
- 标记式注解
- 在 ListDTO 生成时，跳过带此注解的字段

### @AutoEntityFieldDefault

**包路径**：`cn.light.common.anno.AutoEntityFieldDefault`

字段默认值设置（预留功能）。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntityFieldDefault {
    String value() default "";
    boolean notNull() default true;
}
```

### @AutoSorted

**包路径**：`cn.light.common.anno.AutoSorted`

字段排序标记（预留功能）。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoSorted {}
```

---

## Jakarta Validation 注解

以下 validation 注解会被解析并生成到 DTO 中：

| 注解 | 生成格式 |
|------|---------|
| `@NotNull(message="msg")` | `@NotNull(message = "msg")` |
| `@NotBlank(message="msg")` | `@NotBlank(message = "msg")` |
| `@Email(message="msg")` | `@Email(message = "msg")` |
| `@Pattern(regexp="...", message="msg")` | `@Pattern(regexp = "...", message = "msg")` |
| `@Max(value=n, message="msg")` | `@Max(value=n, message="msg")` |
| `@Min(value=n, message="msg")` | `@Min(value=n, message="msg")` |
| `@DecimalMax(value="n", message="msg")` | `@DecimalMax(value = "n", message = "msg")` |
| `@DecimalMin(value="n", message="msg")` | `@DecimalMin(value = "n", message = "msg")` |
| `@Size(min=n, max=m, message="msg")` | `@Size(min=n, max=m, message = "msg")` |

---

## Entity 解析示例

输入 Entity：

```java
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "球队管理")
public class HzTeam extends SysBase {

    @AutoEntityField(value = "球队名称", isKeyName = true)
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "队伍性别")
    @InQueryDTO
    private Integer gender;

    @AutoEntityField(value = "等级")
    private String grade;

    @AutoEntityField(value = "描述")
    private String description;
}
```

解析结果：

```json
{
  "tableName": "HzTeam",
  "className": "Team",
  "EntityName": "team",
  "UpEntityName": "Team",
  "UpTableName": "HzTeam",
  "tableInfo": "球队管理",
  "dbTableName": "hz_team",
  "keyName": "Name",
  "isKey": true,
  "fields": {
    "name": {"type": "String", "label": "球队名称", "inQueryDTO": true, "isKeyName": true},
    "gender": {"type": "Integer", "label": "队伍性别", "inQueryDTO": true},
    "grade": {"type": "String", "label": "等级"},
    "description": {"type": "String", "label": "描述"}
  }
}
```
