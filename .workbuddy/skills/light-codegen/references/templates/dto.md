# DTO 模板 (dto.tpl)

## 原始模板

```
package #(SYS_PATH).packet.dto.#(EntityName);

import #(SYS_PATH).common.annotation.ApiModelPropertyEnum;
import #(SYS_PATH).common.component.IDictionaryObject;
import #(SYS_PATH).common.dto.SysBaseDTO;
import #(SYS_PATH).packet.enums.system.*;
import #(SYS_PATH).packet.enums.business.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
#if(isBigDecimal)
import java.math.BigDecimal;
#end
import java.util.*;
#(import)
/**
 * #(tableInfo) pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class #(UpEntityName)DTO extends SysBaseDTO implements IDictionaryObject {
#(fields)
}
```

## 变量替换说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(fields)` | 字段拼接字符串 | 见下方 |
| `#(import)` | 额外导入语句 | `import cn.light.packet.enums.business.*;` |
| `#if(isBigDecimal)...#end` | 条件块，有 BigDecimal 字段时保留 | `import java.math.BigDecimal;` |

## fields 生成规则

对每个 `@AutoEntityField` 字段（排除 `dto=false`），生成：

```java
    // validation 注解（如有）
    @NotNull(message = "不能为空")

    // Schema 注解
    @Schema(title = "球队名称", description = "SomeEnum或notes")

    // 枚举注解（如有）
    @ApiModelPropertyEnum(SomeEnum.class)

    // 字段声明
    private String name;
```

每两个字段之间空一行。

## 枚举处理

当 `@AutoEntityField.enums()` 不是 `BaseEnum.class` 时：
1. `notes` = 枚举类简单名（如 `GenderEnum`）
2. 如果 `@AutoEntityField.notes()` 非空，追加 notes 内容
3. 生成 `@ApiModelPropertyEnum({EnumName}.class)`
4. 需要添加 import（由 `#(import)` 占位符处理）

## 生成示例

```java
package cn.light.packet.dto.team;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.util.*;

/**
 * 球队管理 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamDTO extends SysBaseDTO implements IDictionaryObject {

    @Schema(title = "球队名称")
    private String name;

    @Schema(title = "队伍性别")
    private Integer gender;

    @Schema(title = "等级")
    private String grade;

    @Schema(title = "描述")
    private String description;

}
```
