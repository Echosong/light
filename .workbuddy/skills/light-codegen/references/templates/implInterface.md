# Service 接口模板 (implInterface.tpl)

## 原始模板

```java
package  #(SYS_PATH).server.service;

import #(SYS_PATH).entity.entity.#(UpTableName);
import #(SYS_PATH).packet.dto.#(EntityName).*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 #(tableInfo) service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #(localDate)
 */
public interface #(UpEntityName)Service extends IService<#(UpTableName)> {

    Page<#(UpEntityName)ListDTO> listPage(#(UpEntityName)QueryDTO queryDTO);

    ResponseEntity<byte[]> export(#(UpEntityName)QueryDTO queryDTO);

    #(UpEntityName)DTO save(#(UpEntityName)DTO #(EntityName)DTO);

    #(UpEntityName)DTO find(Integer id);

    void delete(Integer id);
    #if(isKey)
    List<Map<String, Object>> getMap();
    #end

}
```

## 变量说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(UpTableName)` | 实体类全名 | `HzTeam` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(localDate)` | 当前时间 | `2026-07-04 11:00:00` |
| `#if(isKey)...#end` | 有 keyName 时保留 getMap 方法声明 | - |

## 接口方法列表

| 方法 | 签名 | 说明 |
|------|------|------|
| listPage | `Page<{UpEntityName}ListDTO> listPage({UpEntityName}QueryDTO queryDTO)` | 分页查询 |
| export | `ResponseEntity<byte[]> export({UpEntityName}QueryDTO queryDTO)` | Excel 导出 |
| save | `{UpEntityName}DTO save({UpEntityName}DTO {EntityName}DTO)` | 新增/更新 |
| find | `{UpEntityName}DTO find(Integer id)` | 查询单条 |
| delete | `void delete(Integer id)` | 删除 |
| getMap | `List<Map<String, Object>> getMap()` | 简单下拉数据（仅 isKey=true） |

## 生成示例

```java
package  cn.light.server.service;

import cn.light.entity.entity.HzTeam;
import cn.light.packet.dto.team.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 球队管理 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:00:00
 */
public interface TeamService extends IService<HzTeam> {

    Page<TeamListDTO> listPage(TeamQueryDTO queryDTO);

    ResponseEntity<byte[]> export(TeamQueryDTO queryDTO);

    TeamDTO save(TeamDTO teamDTO);

    TeamDTO find(Integer id);

    void delete(Integer id);
    List<Map<String, Object>> getMap();

}
```
