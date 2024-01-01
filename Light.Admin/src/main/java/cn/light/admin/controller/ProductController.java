package cn.light.admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.product.ProductDTO;
import cn.light.common.dto.product.ProductListDTO;
import cn.light.common.dto.product.ProductQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.KdProduct;
import cn.light.entity.mapper.ProductMapper;
import cn.light.entity.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 产品信息管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 20:15:44</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "产品信息 控制器")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Resource
    private  ProductRepository productRepository;

    @Resource
    private ProductMapper productMapper;

    @Operation(summary = "分页查询产品信息")
    @PutMapping("/listPage")
    public Page<ProductListDTO> listPage(@RequestBody @Valid ProductQueryDTO queryDTO){
        Page<KdProduct> dataPages  =  PageUtil.getPage(productMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ProductListDTO.class);
    }

    @Operation(summary = "新增活更新产品信息")
    @PostMapping("/save")
    @Log("新增|修改产品信息")
    public void save(@RequestBody @Valid ProductDTO productDTO){
        KdProduct kdProduct = DtoMapper.convert(productDTO, KdProduct.class);
        productRepository.save(kdProduct);
    }

    @Operation(summary = "查询全部产品信息")
    @GetMapping("/list")
    public List<ProductListDTO> list(){
        List<KdProduct> all = productRepository.findAll();
        return DtoMapper.convertList(all, ProductListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ProductDTO find(@PathVariable Integer id){
        KdProduct one = productRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ProductDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除产品信息")
    public void delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}