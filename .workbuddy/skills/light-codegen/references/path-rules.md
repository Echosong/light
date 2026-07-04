# 文件输出路径规则

## 配置变量

从 `Light.Generator/src/main/resources/db-dev.setting` 读取：

| 配置项 | 变量名 | 说明 |
|--------|--------|------|
| `path-base` | `ROOT_PATH` | 项目根路径 |
| `path-vue` | `VUE_PATH` | Vue 前端路径 |
| `db-prefix` | `TABLE_PREFIX` | 表前缀 |

`SYS_PATH` 从 Entity 的 `package` 声明推断：
- Entity 包名为 `cn.light.entity.entity` → `SYS_PATH = cn.light`
- `SYS_PATH_PATH` = `SYS_PATH` 的 `.` 替换为 `/`（如 `cn/light`）

---

## 输出路径表

### DTO 文件（3个）

**输出目录**：`{ROOT_PATH}/Light.Packet/src/main/java/{SYS_PATH_PATH}/packet/dto/{className}/`

| 文件 | 完整路径 |
|------|---------|
| DTO | `.../packet/dto/{className}/{className}DTO.java` |
| QueryDTO | `.../packet/dto/{className}/{className}QueryDTO.java` |
| ListDTO | `.../packet/dto/{className}/{className}ListDTO.java` |

> `className` 首字母小写，如 `team`

### Mapper 文件（2个）

| 文件 | 路径 |
|------|------|
| Mapper.java | `{ROOT_PATH}/Light.Entity/src/main/java/{SYS_PATH_PATH}/entity/mapper/{UpEntityName}Mapper.java` |
| Mapper.xml | `{ROOT_PATH}/Light.Entity/src/main/resources/mapper/{UpEntityName}Mapper.xml` |

### Controller 文件

**输出路径**：`{ROOT_PATH}/Light.Admin/src/main/java/{SYS_PATH_PATH}/admin/controller/{UpEntityName}Controller.java`

### Service 文件（2个）

| 文件 | 路径 |
|------|------|
| Service 接口 | `{ROOT_PATH}/Light.Server/src/main/java/{SYS_PATH_PATH}/server/service/{UpEntityName}Service.java` |
| Service 实现 | `{ROOT_PATH}/Light.Server/src/main/java/{SYS_PATH_PATH}/server/service/impl/{UpEntityName}ServiceImpl.java` |

### Vue 视图文件（3个）

**输出目录**：`{VUE_PATH}/{className}/`

> `VUE_PATH` 从 `db-dev.setting` 的 `path-vue` 读取，再根据 tableName 前缀追加子目录：
> - tableName 以 `Sys` 开头 → `VUE_PATH_TEMP + "/system"`
> - 其他 → `VUE_PATH_TEMP + "/business"`
>
> `className` 首字母小写，如 `team`

| 文件 | 路径 |
|------|------|
| list.vue | `{VUE_PATH}/{className}/list.vue` |
| add.vue | `{VUE_PATH}/{className}/add.vue` |
| info.vue | `{VUE_PATH}/{className}/info.vue` |

**示例**：
- `VUE_PATH_TEMP = D:/项目资料/light框架/light-view/src/views`
- `HzTeam` → `VUE_PATH = D:/项目资料/light框架/light-view/src/views/business`
- `className = Team`（首字母小写为 `team`）
- 输出：`D:/项目资料/light框架/light-view/src/views/business/team/list.vue`

### 数据字典（全量生成时）

**输出路径**：`{ROOT_PATH}/doc/data.html`

---

## 包名映射表

| 模块 | PackageName 变量值 | 示例 |
|------|-------------------|------|
| DTO | `{SYS_PATH}.packet.dto.{className}` | `cn.light.packet.dto.team` |
| Mapper.java | `{SYS_PATH}.entity.mapper` | `cn.light.entity.mapper` |
| Controller | `{SYS_PATH}.admin.controller` | `cn.light.admin.controller` |
| Service 接口 | `{SYS_PATH}.server.service` | `cn.light.server.service` |
| Service 实现 | `{SYS_PATH}.server.service.impl` | `cn.light.server.service.impl` |

---

## className 提取规则

```
if tableName.startsWith("Sys"):
    className = tableName.substring(3)        // SysUser -> User
    VUE_PATH = VUE_PATH_TEMP + "/system"
else if tableName.startsWith(TABLE_PREFIX):
    className = tableName.substring(TABLE_PREFIX.length())  // HzTeam -> Team
    VUE_PATH = VUE_PATH_TEMP + "/business"
else:
    className = tableName                      // 无前缀
    VUE_PATH = VUE_PATH_TEMP + "/business"
```

> 注意：`TABLE_PREFIX` 大小写敏感，`startsWith` 用原始大小写比较。
> 但 `db-prefix=hz` 是小写，Entity 类名是 `HzTeam`（首字母大写），
> 实际比较时需要对 tableName 做首字母小写处理：`tableName.toLowerCase().startsWith(TABLE_PREFIX.toLowerCase())`
> 然后截取时用 `StrUtil.subAfter(tableName, TABLE_PREFIX, false)`（忽略大小写取后缀）

---

## 示例（以 HzTeam 为例）

配置：
- `ROOT_PATH = D:/项目资料/light框架/framework`
- `SYS_PATH = cn.light`
- `SYS_PATH_PATH = cn/light`
- `TABLE_PREFIX = hz`
- `tableName = HzTeam`
- `className = Team`

输出文件列表：

```
D:/项目资料/light框架/framework/Light.Packet/src/main/java/cn/light/packet/dto/team/TeamDTO.java
D:/项目资料/light框架/framework/Light.Packet/src/main/java/cn/light/packet/dto/team/TeamQueryDTO.java
D:/项目资料/light框架/framework/Light.Packet/src/main/java/cn/light/packet/dto/team/TeamListDTO.java
D:/项目资料/light框架/framework/Light.Entity/src/main/java/cn/light/entity/mapper/TeamMapper.java
D:/项目资料/light框架/framework/Light.Entity/src/main/resources/mapper/TeamMapper.xml
D:/项目资料/light框架/framework/Light.Admin/src/main/java/cn/light/admin/controller/TeamController.java
D:/项目资料/light框架/framework/Light.Server/src/main/java/cn/light/server/service/TeamService.java
D:/项目资料/light框架/framework/Light.Server/src/main/java/cn/light/server/service/impl/TeamServiceImpl.java
D:/项目资料/light框架/light-view/src/views/business/team/list.vue
D:/项目资料/light框架/light-view/src/views/business/team/add.vue
D:/项目资料/light框架/light-view/src/views/business/team/info.vue
```
