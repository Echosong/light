# Info.vue 模板 (infoVue.tpl)

## 原始模板

见 `infoVue.tpl` 文件。模板中包含以下占位符，需在生成时替换。

## 占位符说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#{descriptions-item}#` | 描述列表项 HTML | 见下方规则 |

## 生成规则

### 描述列表项 `#{descriptions-item}#`

遍历所有带 `@AutoEntityField` 的字段，每个字段生成一个 `<el-descriptions-item>`：

```html
<a-descriptions-item>
    <template #label>
        <div class="cell-item" style="width:100px;">
            {value}
        </div>
    </template>
    {content}
</a-descriptions-item>
```

**{value}** → `@AutoEntityField.value()`（字段中文名）

**{content}** 根据 htmlType 决定：

| htmlType | 生成的 HTML |
|----------|------------|
| `UPLOAD` | `<img :src="one.{dataIndex}" style="width:100px; height:100px" />` |
| 其他 | `<div style="width:500px;" v-html="one.{dataIndex}"></div>` |

**{dataIndex}** → 字段名（如果字段关联了枚举 enums 非 BaseEnum，则 `fieldName + "Enum"`）

各列表项之间直接拼接（无换行分隔）。

> 注意：原 Generator 中 `viewInfo()` 方法被注释掉（`//this.viewInfo(declaredFields)`），
> 本 Skill 中重新启用该功能。原代码中使用 `el-descriptions`（Element Plus 组件），
> 但实际列表项标签用的是 `a-descriptions-item`（Ant Design Vue）。
> 本 Skill 统一使用 `a-descriptions-item` 保持组件一致。

## 生成示例

Entity:
```java
@AutoEntity(value = "球队管理")
public class HzTeam extends SysBase {
    @AutoEntityField(value = "球队名称")
    private String name;

    @AutoEntityField(value = "队伍性别", enums = GenderEnum.class)
    private Integer gender;

    @AutoEntityField(value = "队徽", htmlType = HtmlTypeEnum.UPLOAD)
    private String logo;
}
```

生成的 info.vue 关键部分：

```html
<a-descriptions-item>
    <template #label>
        <div class="cell-item" style="width:100px;">
            球队名称
        </div>
    </template>
    <div style="width:500px;" v-html="one.name"></div>
</a-descriptions-item><a-descriptions-item>
    <template #label>
        <div class="cell-item" style="width:100px;">
            队伍性别
        </div>
    </template>
    <div style="width:500px;" v-html="one.genderEnum"></div>
</a-descriptions-item><a-descriptions-item>
    <template #label>
        <div class="cell-item" style="width:100px;">
            队徽
        </div>
    </template>
    <img :src="one.logo" style="width:100px; height:100px" />
</a-descriptions-item>
```
