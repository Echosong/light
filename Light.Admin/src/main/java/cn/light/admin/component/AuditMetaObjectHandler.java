package cn.light.admin.component;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * 当使用mybatis-plus时，自动填充创建时间、更新时间、创建人、更新人
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/7/27 15:07
 */
@Component
public class AuditMetaObjectHandler implements MetaObjectHandler {

    private Integer getUserId() {
        try {
            return Optional.ofNullable(Convert.toInt(StpUtil.getLoginId())).orElse(0);
        }catch (Exception e) {
            return 0;
        }
    }
    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        if(Objects.isNull(metaObject.getValue("creatorId"))) {
            this.strictInsertFill(metaObject, "creatorId", Integer.class, getUserId());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        if(Objects.isNull(metaObject.getValue("updaterId"))) {
            this.strictInsertFill(metaObject, "updaterId", Integer.class, getUserId());
        }
    }
}

