# Service 实现模板 (implService.tpl)

## 原始模板

```java
package  #(SYS_PATH).server.service.impl;

import cn.hutool.core.date.DateUtil;
import #(SYS_PATH).common.exception.BaseKnownException;
import #(SYS_PATH).common.util.DtoMapper;
import #(SYS_PATH).common.util.ExcelUtil;
import #(SYS_PATH).common.util.PageUtil;

import org.springframework.stereotype.Service;
import #(SYS_PATH).entity.entity.#(UpTableName);
import #(SYS_PATH).entity.mapper.#(UpEntityName)Mapper;
import #(SYS_PATH).server.service.#(UpEntityName)Service;
import #(SYS_PATH).packet.dto.#(EntityName).*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 #(tableInfo) service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #(localDate)
 */
@Service
public class #(UpEntityName)ServiceImpl extends ServiceImpl<#(UpEntityName)Mapper, #(UpTableName)> implements #(UpEntityName)Service {

    @Override
    public Page<#(UpEntityName)ListDTO> listPage(#(UpEntityName)QueryDTO queryDTO){
         Page<#(UpTableName)> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, #(UpEntityName)ListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(#(UpEntityName)QueryDTO queryDTO) {
        List<#(UpTableName)> all = this.baseMapper.listPage(queryDTO);
        String fileName = "#(UpEntityName)"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, #(UpEntityName)ListDTO.class), fileName, #(UpEntityName)ListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public #(UpEntityName)DTO save(#(UpEntityName)DTO saveDTO) {
        #(UpTableName) #(EntityName) = DtoMapper.convert(saveDTO, #(UpTableName).class);
        this.saveOrUpdate(#(EntityName));
        return DtoMapper.convert(#(EntityName), #(UpEntityName)DTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public #(UpEntityName)DTO find(Integer id){
        #(UpTableName) one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, #(UpEntityName)DTO.class);
    }

    #if(isKey)
    @Override
    public List<Map<String, Object>> getMap(){
         List<#(UpTableName)> all = this.baseMapper.selectList(new LambdaQueryWrapper<#(UpTableName)>()
                        .select(#(UpTableName)::getId, #(UpTableName)::get#(keyName) )
                        .orderByDesc(#(UpTableName)::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (#(UpTableName) item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.get#(keyName)());
            maps.add(map);
        }
        return maps;
    }
    #end

}
```

## 变量说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(UpTableName)` | 实体类全名 | `HzTeam` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(EntityName)` | 首字母小写类名 | `team` |
| `#(tableInfo)` | 表说明 | `球队管理` |
| `#(localDate)` | 当前时间 | `2026-07-04 11:00:00` |
| `#(keyName)` | 主名称字段首字母大写 | `Name` |
| `#if(isKey)...#end` | 有 keyName 时保留 getMap 方法 | - |

## 实现的方法

| 方法 | 核心逻辑 |
|------|---------|
| listPage | 调用 Mapper.listPage → PageUtil 分页 → DtoMapper 转 ListDTO |
| export | 查询全部 → DtoMapper 转 ListDTO → ExcelUtil 生成 xlsx |
| save | DtoMapper 转 Entity → saveOrUpdate → 转回 DTO |
| delete | removeById |
| find | getById → Optional + orElseThrow → 转 DTO |
| getMap | selectList(id, keyName) → 转 List<Map> |

## 生成示例（isKey=true, keyName=Name）

```java
package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import org.springframework.stereotype.Service;
import cn.light.entity.entity.HzTeam;
import cn.light.entity.mapper.TeamMapper;
import cn.light.server.service.TeamService;
import cn.light.packet.dto.team.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 球队管理 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:00:00
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, HzTeam> implements TeamService {

    @Override
    public Page<TeamListDTO> listPage(TeamQueryDTO queryDTO){
         Page<HzTeam> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, TeamListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(TeamQueryDTO queryDTO) {
        List<HzTeam> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Team"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, TeamListDTO.class), fileName, TeamListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public TeamDTO save(TeamDTO saveDTO) {
        HzTeam team = DtoMapper.convert(saveDTO, HzTeam.class);
        this.saveOrUpdate(team);
        return DtoMapper.convert(team, TeamDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public TeamDTO find(Integer id){
        HzTeam one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, TeamDTO.class);
    }

    @Override
    public List<Map<String, Object>> getMap(){
         List<HzTeam> all = this.baseMapper.selectList(new LambdaQueryWrapper<HzTeam>()
                        .select(HzTeam::getId, HzTeam::getName )
                        .orderByDesc(HzTeam::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (HzTeam item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            maps.add(map);
        }
        return maps;
    }

}
```
