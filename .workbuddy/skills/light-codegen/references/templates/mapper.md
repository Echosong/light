# Mapper.java 模板 (mapper.tpl)

## 原始模板

```
package #(PackageName).mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)QueryDTO;
import #(SYS_PATH).entity.entity.#(UpTableName);
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 #(tableInfo)
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #(localDate)
 */
@Mapper
public interface #(UpEntityName)Mapper extends BaseMapper<#(UpTableName)> {
    List<#(UpTableName)> listPage(#(UpEntityName)QueryDTO #(EntityName)QueryDTO);
}
```

## 变量说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(PackageName)` | `{SYS_PATH}.entity` | `cn.light.entity` |
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(UpTableName)` | 实体类全名 | `HzTeam` |
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(localDate)` | 当前时间 | `2026-07-04 11:00:00` |

## 生成示例

```java
package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.team.TeamQueryDTO;
import cn.light.entity.entity.HzTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 球队管理
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:00:00
 */
@Mapper
public interface TeamMapper extends BaseMapper<HzTeam> {
    List<HzTeam> listPage(TeamQueryDTO teamQueryDTO);
}
```
