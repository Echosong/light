package cn.light.generator.processor;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.enums.BaseEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.generator.Const;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * <p>Title: </p >
 * <p>Description: 处理数据表Common</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0414:44</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class JpaCommentService extends BaseService implements ServiceInterface {

    JpaCommentService(Class<?> clazz) {
        super(clazz);
    }

    /**
     * 业务处理入库
     */
    @Override
    public void start() {
        if (!Const.UNCOMMENT) {
            log.warn("数据库字段备注 关闭{}", this.tableName);
            return;
        }
        this.tableName = StrUtil.toUnderlineCase(tableName);

        if(clazz.isAnnotationPresent(AutoEntity.class)) {
            try {
                this.setTableComment(autoEntity.value());
            }catch (Exception e){
                log.warn("修改表备注"+e.getMessage());
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(AutoEntityField.class)){
                continue;
            }
            //处理字段
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            try {
                String info = StrUtil.isNotEmpty(annotation.notes()) ? StrUtil.format("({})", annotation.notes()) : "";
                //如果是枚举可以直接把枚举字符
                if(!"BaseEnum".equals(annotation.enums().getSimpleName())){
                    info +=  BaseEnum.getInfo(annotation.enums());
                }
                int length = annotation.len();
                if(annotation.htmlType().equals(HtmlTypeEnum.UPLOAD) && annotation.len() == 255){
                    length = 2000;
                }
                String defaultStr = "";
                if(field.isAnnotationPresent(AutoEntityFieldDefault.class)){
                    AutoEntityFieldDefault aDefault = field.getAnnotation(AutoEntityFieldDefault.class);
                    //不能为空那么肯定有默认值，所以就简单粗暴这样处理了
                    defaultStr = StrUtil.format("default {} {} ",StrUtil.isEmpty(aDefault.value())?"''":aDefault.value(),
                            aDefault.notNull()?"not null": "null");
                }
                setColumnName(field.getName(), annotation.value() + info, field.getType().getSimpleName(),
                        length, defaultStr);
            } catch (SQLException sqlException) {
                log.info(sqlException.getMessage());
            }
        }
    }

    /**
     * 修改表说明
     *
     * @param tableComment 表注释
     */
    private void setTableComment(String tableComment) throws SQLException {
        Db.use().execute(StrUtil.format("alter table {} comment '{}'", tableName, tableComment));
        Console.log("执行表{} 备注 : {}", tableName, tableComment);
    }



    /**
     * 修改字段说明·
     *
     * @param columnName 列名
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private void setColumnName(String columnName, String columnComment, String typeName, int length, String  defaultStr) throws SQLException {
        columnName = StrUtil.toUnderlineCase(columnName);
        String sqlType = StrUtil.format(" varchar({})", length);
        if ("Integer".equals(typeName)) {
            sqlType = "int";
        }
        if ("BigDecimal".equals(typeName) || "float".equalsIgnoreCase(typeName)) {
            sqlType = "decimal(19,2)";
        }
        if ("long".equals(typeName)) {
            sqlType = "bigint";
        }
        if ("Boolean".equalsIgnoreCase(typeName)) {
            sqlType = "bit";
        }

        //设置默认值相关  default 3 not null
        String alertStr = StrUtil.format("alter table {} modify `{}` {} {} comment '{}'",
                tableName, columnName, sqlType, defaultStr, columnComment);
        Db.use().execute(alertStr);
        Console.log("执行表 {} ,的字段{}, 备注 : {}", tableName, columnName, columnComment);
    }


}
