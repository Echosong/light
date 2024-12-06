package cn.light.entity.mapper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.entity.entity.SysCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.Objects;

/**
 * 用来全局生成单据号
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface CodeMapper extends BaseMapper<SysCode> {
    /**
     * 获取单据号
     * @param clazz 表信息
     * @return 单据号
     */
    default String getCode(Class<?> clazz){
        //根据name 查询一条数据
        return getCode(clazz.getSimpleName());
    }

    default String getCode(String name){
        //根据name 查询一条数据
        SysCode code = this.selectOne(new LambdaQueryWrapper<SysCode>().eq(SysCode::getName, name));
        if(Objects.isNull(code)){
            code = new SysCode();
            code.setName(name);
            code.setNumber(1L);
            this.insert(code);
        }else{
            if(code.getNumber() >= 999999){
                code.setNumber(1L);
            }else {
                code.setNumber(code.getNumber() + 1);
            }
            this.updateById(code);
        }
        return DateUtil.format(new Date(), "yyMMdd") + StrUtil.fillBefore(code.getNumber()+"", '0', 6);
    }
}
