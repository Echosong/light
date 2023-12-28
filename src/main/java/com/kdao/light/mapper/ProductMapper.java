package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.common.dto.product.ProductQueryDTO;
import com.kdao.light.entity.KdProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 产品信息
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ProductMapper extends BaseMapper<KdProduct> {
    List<KdProduct> listPage(ProductQueryDTO productQueryDTO);
}
