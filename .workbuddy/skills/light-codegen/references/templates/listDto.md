# ListDTO 模板 (listDto.tpl)

## 原始模板

```
package #(SYS_PATH).packet.dto.#(EntityName);

import #(SYS_PATH).packet.enums.system.*;
import #(SYS_PATH).packet.enums.business.*;
import #(SYS_PATH).common.annotation.ApiModelPropertyEnum;
import #(SYS_PATH).common.component.IDictionaryObject;
import #(SYS_PATH).common.dto.SysBaseDTO;
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
 * #(UpEntityName)列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #(localDate)
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class #(UpEntityName)ListDTO extends SysBaseDTO implements IDictionaryObject {
#(fields)
}
```

## 字段过滤规则

包含所有 `@AutoEntityField` 字段，但排除带 `@NotinListDTO` 注解的字段。

> 注意：与 DTO 不同，ListDTO 不生成 validation 注解（@NotNull 等），只生成 @Schema 和 @ApiModelPropertyEnum。

## 生成示例

```java
package cn.light.packet.dto.team;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.util.*;

/**
 * Team列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:00:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamListDTO extends SysBaseDTO implements IDictionaryObject {

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
