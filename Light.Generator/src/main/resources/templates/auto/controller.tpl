package #(PackageName);
import #(SYS_PATH).common.annotation.*;
import #(SYS_PATH).common.consts.Consts;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)DTO;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)ListDTO;
import #(SYS_PATH).packet.dto.#(EntityName).#(UpEntityName)QueryDTO;
import #(SYS_PATH).server.service.#(UpEntityName)Service;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  #(tableInfo) 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : #(localDate)
 */
@Tag(name = "#(tableInfo) 控制器")
@RestController
@RequestMapping("/#(EntityName)")
public class #(UpEntityName)Controller extends BaseController{

    @Resource
    private #(UpEntityName)Service #(EntityName)Service;

    @Operation(summary = "分页查询#(tableInfo)")
    @PutMapping("/listPage")
    public Page<#(UpEntityName)ListDTO> listPage(@RequestBody @Valid #(UpEntityName)QueryDTO queryDTO){
        return #(EntityName)Service.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出#(tableInfo)")
    public ResponseEntity<byte[]> export(@RequestBody @Valid #(UpEntityName)QueryDTO queryDTO) {
        return #(EntityName)Service.export(queryDTO);
    }

    @Operation(summary = "新增活更新#(tableInfo)")
    @PostMapping("/save")
    @Log("新增|修改#(tableInfo)")
    public void save(@RequestBody @Valid #(UpEntityName)DTO #(EntityName)DTO){
         #(EntityName)Service.save(#(EntityName)DTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public #(UpEntityName)DTO find(@PathVariable Integer id){
        return #(EntityName)Service.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除#(tableInfo)")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        #(EntityName)Service.delete(id);
    }

    #if(isKey)
    @Operation(summary = "简单查询#(tableInfo)")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         return #(EntityName)Service.getMap();
    }
    #end

}