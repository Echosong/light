package #{PackageName}#;

import #{SYS_PATH}.common.dto.#{EntityName}#.#{UpEntityName}#DTO;
import #{SYS_PATH}.common.dto.#{EntityName}#.#{UpEntityName}#ListDTO;
import #{SYS_PATH}.common.dto.#{EntityName}#.#{UpEntityName}#QueryDTO;
import #{SYS_PATH}.common.exception.BaseKnownException;
import #{SYS_PATH}.common.utils.DtoMapper;
import #{SYS_PATH}.entity.#{UpTableName}#;
import #{SYS_PATH}.repository.#{UpEntityName}#Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: #{tableInfo}#管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: #{localDate}#</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Api(tags = "#{tableInfo}# 控制器")
@RestController
@RequestMapping("/#{EntityName}#")
public class #{UpEntityName}#Controller extends BaseController{

    private final #{UpEntityName}#Repository #{EntityName}#Repository;

    @Autowired
    public #{UpEntityName}#Controller(#{UpEntityName}#Repository #{EntityName}#Repository) {
        this.#{EntityName}#Repository = #{EntityName}#Repository;
    }


    @ApiOperation("分页查询#{tableInfo}#")
    @PutMapping("/listPage")
    public Page<#{UpEntityName}#ListDTO> listPage(@RequestBody @Valid #{UpEntityName}#QueryDTO queryDTO){
        Page<#{UpTableName}#> dataPages = #{EntityName}#Repository.listPage(#{queryParams}# queryDTO.getRequest());
        return DtoMapper.convertPage(dataPages, #{UpEntityName}#ListDTO.class);
    }

    @ApiOperation("新增活更新#{tableInfo}#")
    @PostMapping("/save")
    @Log("新增|修改#{tableInfo}#")
    public void save(@RequestBody @Valid #{UpEntityName}#DTO #{EntityName}#DTO){
        #{UpTableName}# #{TableName}# = DtoMapper.convert(#{EntityName}#DTO, #{UpTableName}#.class);
        #{EntityName}#Repository.save(#{TableName}#);
    }

    @ApiOperation("查询全部#{tableInfo}#")
    @GetMapping("/list")
    public List<#{UpEntityName}#ListDTO> list(){
        List<#{UpTableName}#> all = #{EntityName}#Repository.findAll();
        return DtoMapper.convertList(all, #{UpEntityName}#ListDTO.class);
    }

    @ApiOperation("查询")
    @GetMapping("/find/{id}")
    public #{UpEntityName}#DTO find(@PathVariable Integer id){
        #{UpTableName}# one = #{EntityName}#Repository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, #{UpEntityName}#DTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除")
    @Log("删除#{tableInfo}#")
    public void delete(@PathVariable Integer id) {
        #{EntityName}#Repository.deleteById(id);
    }

}