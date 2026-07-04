# List.vue 模板 (listVue.tpl)

## 原始模板

见 `listVue.tpl` 文件。模板中包含以下占位符，需在生成时替换。

## 占位符说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#{a-form-item}#` | 查询表单项 HTML（多行拼接） | 见下方规则 |
| `#{listColumns}#` | 表格列 JSON 数组 | `[{"title":"球队名称","dataIndex":"name","ellipsis":true},...]` |
| `#{queryPageParams}#` | 查询参数对象 | `{pageSize:15,page:1,total:0,name:''}` |
| `#{fixed}#` | 操作列固定方式 | `fixed="right"` 或 空字符串 |
| `//importFiles` | 额外 import 语句（多行） | `import InputEnum from "/@/components/..."` |

## 生成规则

### 1. 查询表单项 `#{a-form-item}#`

遍历所有带 `@AutoEntityField` 的字段，如果字段有 `@InQueryDTO`，根据字段类型生成查询表单项：

| 字段类型/条件 | 生成的 HTML |
|--------------|------------|
| `String` | `<a-form-item label="{value}" v-if="!query.{fieldName}" class="smart-query-form-item"><a-input v-model:value="p.{fieldName}" placeholder="模糊查询" :allow-clear="true"></a-input></a-form-item>` |
| `Date` | `<a-form-item label="{value}" v-if="!query.{fieldName}" class="smart-query-form-item"><a-range-picker show-time @change="(rangDate)=> {p.start{UpperFieldName} = rangDate[0];p.end{UpperFieldName} = rangDate[1];}" /></a-form-item>` |
| 枚举（enums 非 BaseEnum） | `<a-form-item label="{value}" v-if="!query.{fieldName}" class="smart-query-form-item"><input-enum enumName="{enumNameLower}" style="width: 180px;" v-model="p.{fieldName}"></input-enum></a-form-item>` |
| `htmlType == SELECT` | `<a-form-item label="{value}" v-if="!query.{fieldName}" class="smart-query-form-item"><select-data v-model:value="p.{fieldName}" routeName="{sourceLower}"></select-data></a-form-item>` |

> 注意：SELECT 类型的表单项会覆盖枚举类型的表单项（优先级更高）。如果 `htmlType == SELECT`，不生成枚举查询项。

各表单项之间用 `\r\n` 连接。

**变量映射**：
- `{value}` → `@AutoEntityField.value()`（字段中文名）
- `{fieldName}` → 字段名（如 `name`）
- `{UpperFieldName}` → 首字母大写字段名（如 `Name`，用于 Date 类型的 start/end）
- `{enumNameLower}` → 枚举类名首字母小写（如 `genderEnum` → `genderEnum`，实际是 `StrUtil.lowerFirst(lclazz.getSimpleName())`）
- `{sourceLower}` → `@AutoEntityField.source()` 首字母小写

### 2. 表格列 `#{listColumns}#`

生成 JSON 数组，每个带 `@AutoEntityField` 且**没有** `@NotinListDTO` 的字段生成一个列对象：

```json
{"title":"{value}","dataIndex":"{dataIndex}","ellipsis":true}
```

- `title` → `@AutoEntityField.value()`
- `dataIndex` → 字段名（如果字段关联了枚举，则 `fieldName + "Enum"`）
- `ellipsis` → 固定 `true`
- `sorter` → 如果字段有 `@AutoSorted` 注解，则 `"sorter":true`

最后追加一个操作列：
```json
{"title":"操作","width":90,"fixed":"right","dataIndex":"action"}
```

> 注意：所有列对象合并为一个 JSON 数组字符串。

### 3. 查询参数 `#{queryPageParams}#`

格式：`{pageSize:15,page:1,total:0, {查询字段}}`

- `pageSize` → 固定 `15`
- `page` → 固定 `1`
- `total` → 固定 `0`
- 查询字段：遍历 `@InQueryDTO` 字段
  - Date 类型 → `start{UpperFieldName}:'', end{UpperFieldName}:''`
  - 其他类型 → `{fieldName}:''`
- 如果没有查询字段，格式为 `{pageSize:15,page:1, total: 0}`
- 如果有查询字段，格式为 `{pageSize:15,page:1, total: 0, {field1}:'', {field2}:''}`

### 4. 操作列固定 `#{fixed}#`

- 表格列数（含操作列）>= 10 时 → `fixed="right"`
- 否则 → 空字符串

> 列数计算：从 `@AutoEntityField` 且非 `@NotinListDTO` 的字段数 + 1（ID 编号列） + 1（操作列）

### 5. import 语句 `//importFiles`

根据字段类型收集 import（去重）：

| 条件 | import 语句 |
|------|------------|
| `htmlType == UPLOAD` | `import Preview from "/@/components/framework/base-preview-image/index.vue";` |
| `htmlType == FILE` | `import Link from "/@/components/framework/base-preview-file/index.vue";` |
| `htmlType == RADIO` | `import ESwitch from "/@/components/framework/base-switch/index.vue";` |
| `htmlType == SELECT` | `import selectData from '/@/components/framework/base-map/index.vue'` |
| 查询表单项含 `<input-enum` | `import InputEnum from "/@/components/framework/base-enum/index.vue";` |

> 注意：UPLOAD/FILE/RADIO/SELECT 的 import 是遍历所有字段判断的，不限于 `@InQueryDTO` 字段。
> InputEnum 的 import 是判断最终拼接的 `elContent` 是否包含 `<input-enum` 字符串。

## 生成示例

Entity:
```java
@AutoEntity(value = "球队管理")
public class HzTeam extends SysBase {
    @AutoEntityField(value = "球队名称", isKeyName = true)
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "队伍性别", enums = GenderEnum.class)
    @InQueryDTO
    private Integer gender;

    @AutoEntityField(value = "等级")
    private String grade;
}
```

生成的 list.vue 关键部分：

```html
<!-- 查询表单 -->
<a-form-item label="球队名称" v-if="!query.name" class="smart-query-form-item">
    <a-input v-model:value="p.name" placeholder="模糊查询" :allow-clear="true"></a-input>
</a-form-item>
<a-form-item label="队伍性别" v-if="!query.gender" class="smart-query-form-item">
    <input-enum enumName="genderEnum" style="width: 180px;" v-model="p.gender"></input-enum>
</a-form-item>
```

```javascript
// columns
const columns = ref([{"title":"球队名称","dataIndex":"name","ellipsis":true},{"title":"队伍性别","dataIndex":"genderEnum","ellipsis":true},{"title":"等级","dataIndex":"grade","ellipsis":true},{"title":"操作","width":90,"fixed":"right","dataIndex":"action"}]);

// query params
const params = {pageSize:15,page:1,total:0, name:'', gender:''};

// imports
import InputEnum from "/@/components/framework/base-enum/index.vue";
```
