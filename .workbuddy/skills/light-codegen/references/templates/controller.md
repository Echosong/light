# Controller 模板 (controller.tpl)

## 原始模板

```java
package #(PackageName);
import #(SYS_PATH).common.annotation.*;
import #(SYS_PATH).common.consts.Consts;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)DTO;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)ListDTO;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)QueryDTO;
import #(SYS_PATH).server.service.#(UpEntityName)Service;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  #(tableInfo) 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : #(localDate)
 */
@Tag(name = "#(tableInfo) 控制器")
@RestController
@RequestMapping("/#(EntityName)")
public class #(UpEntityName)Controller extends BaseController{

    @Resource
    private #(UpEntityName)Service #(EntityName)Service;

    @Operation(summary = "分页查询#(tableInfo)")
    @PutMapping("/listPage")
    public Page<#(UpEntityName)ListDTO> listPage(@RequestBody @Valid #(UpEntityName)QueryDTO queryDTO){
        return #(EntityName)Service.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出#(tableInfo)")
    public ResponseEntity<byte[]> export(@RequestBody @Valid #(UpEntityName)QueryDTO queryDTO) {
        return #(EntityName)Service.export(queryDTO);
    }

    @Operation(summary = "新增活更新#(tableInfo)")
    @PostMapping("/save")
    @Log("新增|修改#(tableInfo)")
    public void save(@RequestBody @Valid #(UpEntityName)DTO #(EntityName)DTO){
         #(EntityName)Service.save(#(EntityName)DTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public #(UpEntityName)DTO find(@PathVariable Integer id){
        return #(EntityName)Service.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除#(tableInfo)")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        #(EntityName)Service.delete(id);
    }

    #if(isKey)
    @Operation(summary = "简单查询#(tableInfo)")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         return #(EntityName)Service.getMap();
    }
    #end

}
```

## 变量说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(PackageName)` | `{SYS_PATH}.admin.controller` | `cn.light.admin.controller` |
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(localDate)` | 当前时间 | `2026-07-04 11:00:00` |
| `#if(isKey)...#end` | 有 keyName 时保留 getMap 方法 | - |

## 包含的 REST API

| 方法 | HTTP | 路径 | 说明 |
|------|------|------|------|
| listPage | PUT | `/{EntityName}/listPage` | 分页查询 |
| export | PUT | `/{EntityName}/export` | Excel 导出 |
| save | POST | `/{EntityName}/save` | 新增/更新 |
| find | GET | `/{EntityName}/find/{id}` | 查询单条 |
| delete | DELETE | `/{EntityName}/delete/{id}` | 删除 |
| getMap | GET | `/{EntityName}/getMap` | 简单下拉数据（仅 isKey=true 时） |

## 生成示例（isKey=true）

```java
package cn.light.admin.controller;

import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.team.TeamDTO;
import cn.light.packet.dto.team.TeamListDTO;
import cn.light.packet.dto.team.TeamQueryDTO;
import cn.light.server.service.TeamService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  球队管理 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2026-07-04 11:00:00
 */
@Tag(name = "球队管理 控制器")
@RestController
@RequestMapping("/team")
public class TeamController extends BaseController{

    @Resource
    private TeamService teamService;

    @Operation(summary = "分页查询球队管理")
    @PutMapping("/listPage")
    public Page<TeamListDTO> listPage(@RequestBody @Valid TeamQueryDTO queryDTO){
        return teamService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出球队管理")
    public ResponseEntity<byte[]> export(@RequestBody @Valid TeamQueryDTO queryDTO) {
        return teamService.export(queryDTO);
    }

    @Operation(summary = "新增活更新球队管理")
    @PostMapping("/save")
    @Log("新增|修改球队管理")
    public void save(@RequestBody @Valid TeamDTO teamDTO){
         teamService.save(teamDTO);
    }

    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public TeamDTO find(@PathVariable Integer id){
        return teamService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除球队管理")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        teamService.delete(id);
    }

    @Operation(summary = "简单查询球队管理")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         return teamService.getMap();
    }

}
```
