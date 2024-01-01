<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="#{PackageName}#.mapper.#{UpEntityName}#Mapper">
    <select id="listPage" resultType="#{SYS_PATH}.entity.entity.#{UpTableName}#">
        select *
        from #{table_name}# where 1=1
                 #{sql_where}#
    </select>

</mapper>