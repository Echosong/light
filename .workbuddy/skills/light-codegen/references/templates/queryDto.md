# QueryDTO 模板 (queryDto.tpl)

## 原始模板

```
package #(SYS_PATH).packet.dto.#(EntityName);

import #(SYS_PATH).packet.enums.system.*;
import #(SYS_PATH).packet.enums.business.*;
import #(SYS_PATH).common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import #(SYS_PATH).common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
#if(isBigDecimal)
import java.math.BigDecimal;
#end
import java.util.*;
#(import)
/**
* #(UpEntityName)QueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : #(localDate)
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class #(UpEntityName)QueryDTO extends PageInfo {
#(fields)
}
```

## 字段过滤规则

只包含带 `@InQueryDTO` 注解的字段。

### Date 类型特殊处理

Date 类型字段生成两个查询字段：

```java
    @Schema(title = "创建时间")
    private Date startDate;

    @Schema(title = "创建时间")
    private Date endDate;
```

字段名规则：
- `start` + `upperFirst(fieldName)` → `startDate`
- `end` + `upperFirst(fieldName)` → `endDate`

### 其他类型

正常生成一个字段，包含 validation 注解、Schema 注解、枚举注解。

## 继承关系

QueryDTO 继承 `PageInfo`（分页信息基类），不继承 `SysBaseDTO`。

## 生成示例

```java
package cn.light.packet.dto.team;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.util.*;

/**
* TeamQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2026-07-04 11:00:00
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQueryDTO extends PageInfo {

    @Schema(title = "球队名称")
    private String name;

    @Schema(title = "队伍性别")
    private Integer gender;

}
```
