package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.entity.KdUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>Title: light</p >
 * <p>Description: UserMapper</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023/12/28 16:56 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<KdUser> {

}
