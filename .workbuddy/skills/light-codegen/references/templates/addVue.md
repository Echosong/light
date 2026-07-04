# Add.vue 模板 (addVue.tpl)

## 原始模板

见 `addVue.tpl` 文件。模板中包含以下占位符，需在生成时替换。

## 占位符说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#{a-form-item}#` | 表单项 HTML（多行拼接） | 见下方规则 |
| `//rule_fields` | 验证规则对象属性 | `name:[{required:true,...}],` |
| `//data_init` | 初始数据对象 | `{name:'',gender:0}` |
| `//import_file` | 额外 import 语句 | `import InputEnum from "..."` |
| `//create_editor` | WangEditor ref 声明 | `const content = ref()` |
| `//replace_editor` | WangEditor 内容提取 | `let contentText = content.value.getHtml()...` |

## 生成规则

遍历所有带 `@AutoEntityField` 的字段，对每个字段同时生成：验证规则、初始数据、表单项。

### 1. 验证规则 `//rule_fields`

根据 Jakarta Validation 注解生成验证规则：

格式：`{fieldName}:[{rules}],`

| 注解 | 生成的规则 |
|------|-----------|
| `@NotNull` | `{required: true, message: '请输入{value}', trigger: 'blur' },` |
| `@Length(min=x,max=y)` | `{ min: {min}, max: {max}, message: '长度在 {min} 到 {max} 个字符', trigger: 'blur' },` |
| `@Email` | `{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }` |
| `@Range(min=x)` | `{pattern:/^\d{x,}$/, message:'{value}必须为数字类型', trigger: 'blur'},` |
| `@Pattern(regexp="...",message="")` | `{pattern:/{regexp}/, message:'', trigger: 'blur'}` |

> `{value}` → `@AutoEntityField.value()`（字段中文名）
> 多个注解的规则拼接在一个数组内
> 如果字段没有任何 validation 注解，生成 `{fieldName}:[],`

### 2. 初始数据 `//data_init`

格式：`{ {field1}:'{defaultValue}', {field2}:{defaultValue}, ... }`

| 字段类型 | 默认值 |
|---------|--------|
| `String` | `''`（空字符串） |
| `Date` | `''`（空字符串） |
| 其他（Integer, BigDecimal 等） | `0` |

### 3. 表单项 `#{a-form-item}#`

根据 `@AutoEntityField.htmlType()` 和字段类型生成表单项。

#### 公共属性

- `label="{value}"` → `@AutoEntityField.value()`
- `v-if="!query.{fieldName}"` → 路由参数隐藏控制
- `tooltip="{notes}"` → 当 `notes.length > 4` 时添加

#### 表单项生成顺序（优先级从高到低）

**Step 1: htmlType != INPUT 时，按 htmlType 生成：**

| htmlType | 生成的 HTML |
|----------|------------|
| `RADIO` | `<e-switch v-model:value="m.{fieldNameLower}"></e-switch>` |
| `SELECT` | `<select-data v-model:value="m.{fieldNameLower}" routeName="{sourceLower}"></select-data>` |
| `TEXTAREA` | `<a-textarea rows="2" placeholder="{value}" v-model:value="m.{fieldNameLower}"></a-textarea>` |
| `UPLOAD` | `<FileImage :key="m.{fieldName}" @onremove="m.{fieldName} = ''" @onSuccess="m.{fieldName} = $event" :file="m.{fieldName}">` |
| `FILE` | `<File :key="m.{fieldName}" @onremove="m.{fieldName} = ''" @onSuccess="m.{fieldName} = $event" :file="m.{fieldName}">` |
| `TEXTEDIT` | `<Wangeditor ref="{fieldName}" :modelValue="m.{fieldNameLower}"></Wangeditor>` |

> `htmlType != INPUT` 的字段生成后 `continue`，不再走 Step 2/3。

**Step 2: htmlType == INPUT 且有枚举（enums 非 BaseEnum）时：**

```html
<a-form-item label="{value}" v-if="!query.{fieldName}" {tooltip}>
    <input-enum enumName="{enumNameLower}" v-model:value="m.{fieldName}"></input-enum>
</a-form-item>
```

> 有枚举的字段生成后 `continue`，不再走 Step 3。

**Step 3: htmlType == INPUT 且无枚举时：**

Date 类型：
```html
<a-form-item label="{value}：" prop="{fieldName}" v-if="!query.{fieldName}" {tooltip}>
    <a-date-picker v-model:value="m.{fieldName}" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="{value}"></a-date-picker>
</a-form-item>
```

其他类型：
```html
<a-form-item label="{value}" prop="{fieldName}" v-if="!query.{fieldName}" {tooltip}>
    <a-input v-model:value="m.{fieldName}" :allow-clear="true"></a-input>
</a-form-item>
```

> 注意：Date 类型的 label 后面有全角冒号 `：`，其他类型没有。

各表单项之间用 `\r\n` 连接。

**变量映射**：
- `{fieldName}` → 字段名（原始，如 `createTime`）
- `{fieldNameLower}` → `StrUtil.lowerFirst(fieldName)`（首字母小写，用于 v-model）
- `{value}` → `@AutoEntityField.value()`
- `{sourceLower}` → `StrUtil.lowerFirst(@AutoEntityField.source())`
- `{enumNameLower}` → `StrUtil.lowerFirst(枚举类.getSimpleName())`
- `{tooltip}` → `tooltip="{notes}"`（当 notes.length > 4）或空字符串

### 4. import 语句 `//import_file`

根据字段类型收集 import（去重）：

| 条件 | import 语句 |
|------|------------|
| `htmlType == SELECT` | `import selectData from '/@/components/framework/base-map/index.vue'` |
| `htmlType == UPLOAD` | `import FileImage from "/@/components/framework/base-upload-image/index.vue";` |
| `htmlType == FILE` | `import File from "/@/components/framework/base-upload-file/index.vue";` |
| `htmlType == TEXTEDIT` | `import Wangeditor from '/@/components/framework/wangeditor/index.vue';` |
| 枚举非 BaseEnum | `import InputEnum from "/@/components/framework/base-enum/index.vue";` |

### 5. WangEditor 处理

如果存在 `htmlType == TEXTEDIT` 的字段：

**`//create_editor`** 替换为：
```javascript
const {fieldName1} = ref()
const {fieldName2} = ref()
```

**`//replace_editor`** 替换为：
```javascript
let {fieldName1}Text = {fieldName1}.value.getHtml()
if({fieldName1}Text){
    m.value.{fieldName1} = {fieldName1}Text
}
let {fieldName2}Text = {fieldName2}.value.getHtml()
if({fieldName2}Text){
    m.value.{fieldName2} = {fieldName2}Text
}
```

如果不存在 TEXTEDIT 字段，两个占位符都替换为空字符串。

## 生成示例

Entity:
```java
@AutoEntity(value = "球队管理")
public class HzTeam extends SysBase {
    @AutoEntityField(value = "球队名称", isKeyName = true)
    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @AutoEntityField(value = "队伍性别", enums = GenderEnum.class)
    @InQueryDTO
    private Integer gender;

    @AutoEntityField(value = "描述", htmlType = HtmlTypeEnum.TEXTAREA, notes = "请输入详细描述信息")
    private String description;
}
```

生成的 add.vue 关键部分：

```html
<!-- 表单项 -->
<a-form-item label="球队名称" prop="name" v-if="!query.name" >
    <a-input v-model:value="m.name" :allow-clear="true"></a-input>
</a-form-item>
<a-form-item label="队伍性别" v-if="!query.gender" >
    <input-enum enumName="genderEnum" v-model:value="m.gender"></input-enum>
</a-form-item>
<a-form-item label="描述" v-if="!query.description" tooltip="请输入详细描述信息">
    <a-textarea rows="2" placeholder="描述" v-model:value="m.description"></a-textarea>
</a-form-item>
```

```javascript
// rules
const rules = {
    name:[{required: true, message: '请输入球队名称', trigger: 'blur' },{ min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' },],
    gender:[],
    description:[],
}

// data_init
let initialData  = {name:'', gender:0, description:''}

// imports
import InputEnum from "/@/components/framework/base-enum/index.vue";

// create_editor (空，无 TEXTEDIT)
// replace_editor (空，无 TEXTEDIT)
```
