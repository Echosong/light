# Mapper.xml 模板 (mapperXml.tpl)

## 原始模板

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="#(PackageName).mapper.#(UpEntityName)Mapper">
    <select id="listPage" resultType="#(SYS_PATH).entity.entity.#(UpTableName)">
        select *
        from #(tableName)
        <where>
          #(sql_where)
        </where>
    </select>

</mapper>
```

## 变量说明

| 占位符 | 替换为 | 示例 |
|--------|--------|------|
| `#(PackageName)` | `{SYS_PATH}.entity` | `cn.light.entity` |
| `#(UpEntityName)` | 首字母大写类名 | `Team` |
| `#(SYS_PATH)` | 项目包路径 | `cn.light` |
| `#(UpTableName)` | 实体类全名 | `HzTeam` |
| `#(tableName)` | 数据库表名（下划线） | `hz_team` |
| `#(sql_where)` | 查询条件 XML 片段 | 见下方 |

## sql_where 生成规则

遍历所有 `@AutoEntityField` + `@InQueryDTO` 字段，根据类型生成 `<if>` 条件：

### String 类型

```xml
<if test="name != null and name != '' ">
 and name like CONCAT('%',#{name},'%')
 </if>
```

> 字段名使用下划线格式：`StrUtil.toUnderlineCase(fieldName).toLowerCase()`

### Date 类型

生成两个条件（大于开始、小于结束）：

```xml
<if test="startDate != null ">
 and create_time &gt; #{startDate}
 </if>
<if test="endDate != null ">
 and create_time  &lt; #{endDate}
</if>
```

### 其他类型

```xml
<if test="gender != null ">
 and gender = #{gender}
 </if>
```

### 多个条件拼接

多个 `<if>` 之间用 `\n` 换行连接。

## 生成示例

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.light.entity.mapper.TeamMapper">
    <select id="listPage" resultType="cn.light.entity.entity.HzTeam">
        select *
        from hz_team
        <where>
          <if test="name != null and name != '' ">
 and name like CONCAT('%',#{name},'%')
 </if>
<if test="gender != null ">
 and gender = #{gender}
 </if>
        </where>
    </select>

</mapper>
```
