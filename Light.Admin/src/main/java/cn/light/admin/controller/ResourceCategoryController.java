package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.packet.dto.resourceCategory.ResourceCategoryDTO;
import cn.light.packet.dto.resourceCategory.ResourceCategoryListDTO;
import cn.light.packet.dto.resourceCategory.ResourceCategoryQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysResourceCategory;
import cn.light.entity.mapper.ResourceCategoryMapper;
import cn.light.entity.repository.ResourceCategoryRepository;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 资源分类管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-07 14:35:41</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "资源分类 控制器")
@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController extends BaseController{
    @Resource
    private  ResourceCategoryRepository resourceCategoryRepository;

    @Resource
    private ResourceCategoryMapper resourceCategoryMapper;

    @Operation(summary = "分页查询资源分类")
    @PutMapping("/listPage")
    public Page<ResourceCategoryListDTO> listPage(@RequestBody @Valid ResourceCategoryQueryDTO queryDTO){
        Page<SysResourceCategory> dataPages  =  PageUtil.getPage(resourceCategoryMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ResourceCategoryListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出资源分类")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ResourceCategoryQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysResourceCategory> all = resourceCategoryMapper.listPage(queryDTO);
        String fileName = "ResourceCategory"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ResourceCategoryListDTO.class), fileName, ResourceCategoryListDTO.class);
    }

    @Operation(summary = "新增活更新资源分类")
    @PostMapping("/save")
    @Log("新增|修改资源分类")
    public void save(@RequestBody @Valid ResourceCategoryDTO resourceCategoryDTO){
        SysResourceCategory sysResourceCategory = DtoMapper.convert(resourceCategoryDTO, SysResourceCategory.class);
        resourceCategoryRepository.save(sysResourceCategory);
    }

    @Operation(summary = "查询全部资源分类")
    @GetMapping("/list")
    public List<ResourceCategoryListDTO> list(){
        List<SysResourceCategory> all = resourceCategoryRepository.findAll();
        return DtoMapper.convertList(all, ResourceCategoryListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ResourceCategoryDTO find(@PathVariable Integer id){
        SysResourceCategory one = resourceCategoryRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ResourceCategoryDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除资源分类")
    public void delete(@PathVariable(value="id") Integer id) {
        resourceCategoryRepository.deleteById(id);
    }

    
    @Operation(summary = "简单查询资源分类")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         List<SysResourceCategory> all = resourceCategoryMapper.selectList(new LambdaQueryWrapper<SysResourceCategory>()
                        .select(SysResourceCategory::getId, SysResourceCategory::getTitle)
                        .orderByDesc(SysResourceCategory::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysResourceCategory item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getTitle());
            maps.add(map);
        }
        return maps;
    }
    

}