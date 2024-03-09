package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.entity.entity.SdActivityCategory;
import cn.light.packet.dto.club.ClubDTO;
import cn.light.packet.dto.club.ClubListDTO;
import cn.light.packet.dto.club.ClubQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SdClub;
import cn.light.entity.mapper.ClubMapper;
import cn.light.entity.repository.ClubRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.date.DateUtil;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 俱乐部管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-14 21:03:37</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "俱乐部 控制器")
@RestController
@RequestMapping("/club")
public class ClubController extends BaseController{
    @Resource
    private  ClubRepository clubRepository;

    @Resource
    private ClubMapper clubMapper;

    @Operation(summary = "分页查询俱乐部")
    @PutMapping("/listPage")
    public Page<ClubListDTO> listPage(@RequestBody @Valid ClubQueryDTO queryDTO){
        Page<SdClub> dataPages  =  PageUtil.getPage(clubMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ClubListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出俱乐部")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ClubQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SdClub> all = clubMapper.listPage(queryDTO);
        String fileName = "Club"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ClubListDTO.class), fileName, ClubListDTO.class);
    }

    @Operation(summary = "新增活更新俱乐部")
    @PostMapping("/save")
    @Log("新增|修改俱乐部")
    public void save(@RequestBody @Valid ClubDTO clubDTO){
        SdClub sdClub = DtoMapper.convert(clubDTO, SdClub.class);
        clubRepository.save(sdClub);
    }

    @Operation(summary = "查询全部俱乐部")
    @GetMapping("/list")
    public List<ClubListDTO> list(){
        List<SdClub> all = clubRepository.findAll();
        return DtoMapper.convertList(all, ClubListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ClubDTO find(@PathVariable Integer id){
        SdClub one = clubRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ClubDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除俱乐部")
    public void delete(@PathVariable Integer id) {
        clubRepository.deleteById(id);
    }

    @Operation(summary = "简单查询俱乐部")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
        List<SdClub> all = clubMapper.selectList(new LambdaQueryWrapper<SdClub>()
                .select(SdClub::getId, SdClub::getName)
                .orderByDesc(SdClub::getId)
        );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SdClub item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            maps.add(map);
        }
        return maps;
    }

}