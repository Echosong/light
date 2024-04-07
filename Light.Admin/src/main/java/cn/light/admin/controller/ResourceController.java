package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.entity.entity.SysResourceCategory;
import cn.light.entity.mapper.ResourceCategoryMapper;
import cn.light.entity.repository.ResourceCategoryRepository;
import cn.light.packet.dto.resource.ResourceDTO;
import cn.light.packet.dto.resource.ResourceListDTO;
import cn.light.packet.dto.resource.ResourceQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysResource;
import cn.light.entity.mapper.ResourceMapper;
import cn.light.entity.repository.ResourceRepository;
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
import java.util.stream.Collectors;

/**
 * <p>Title: </p >
 * <p>Description: 办公资源文件存在管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-06 11:17:53</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "办公资源文件存在 控制器")
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController{
    @Resource
    private  ResourceRepository resourceRepository;
    @Resource
    private ResourceCategoryMapper resourceCategoryMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @Operation(summary = "分页查询办公资源文件存在")
    @PutMapping("/listPage")
    public Page<ResourceListDTO> listPage(@RequestBody @Valid ResourceQueryDTO queryDTO){
        Page<SysResource> dataPages  =  PageUtil.getPage(resourceMapper::listPage, queryDTO);
        Set<String> cateIds = dataPages.getContent().stream().map(SysResource::getType).collect(Collectors.toSet());
        List<SysResourceCategory> sysResourceCategories = resourceCategoryMapper.selectBatchIds(cateIds);
        Page<ResourceListDTO> resourceListPage = DtoMapper.convertPage(dataPages, ResourceListDTO.class);
        for (ResourceListDTO resourceListDTO : resourceListPage.getContent()) {
            sysResourceCategories.stream().filter(t->Objects.equals(t.getId().toString(), resourceListDTO.getType()))
                    .findFirst()
                    .ifPresent(t->resourceListDTO.setCategory(t.getTitle()));
        }
        return resourceListPage;
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出办公资源文件存在")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ResourceQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysResource> all = resourceMapper.listPage(queryDTO);
        String fileName = "Resource"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ResourceListDTO.class), fileName, ResourceListDTO.class);
    }

    @Operation(summary = "新增活更新办公资源文件存在")
    @PostMapping("/save")
    @Log("新增|修改办公资源文件存在")
    public void save(@RequestBody @Valid ResourceDTO resourceDTO){
        SysResource sysResource = DtoMapper.convert(resourceDTO, SysResource.class);
        resourceRepository.save(sysResource);
    }

    @Operation(summary = "查询全部办公资源文件存在")
    @GetMapping("/list")
    public List<ResourceListDTO> list(){
        List<SysResource> all = resourceRepository.findAll();
        return DtoMapper.convertList(all, ResourceListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ResourceDTO find(@PathVariable(value = "id") Integer id){
        SysResource one = resourceRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ResourceDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除办公资源文件存在")
    public void delete(@PathVariable Integer id) {
        resourceRepository.deleteById(id);
    }

    

}