package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.ad.AdDTO;
import cn.light.packet.dto.ad.AdListDTO;
import cn.light.packet.dto.ad.AdQueryDTO;
import cn.light.server.service.AdService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  广告管理 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-10-05 21:07:18
 */
@Tag(name = "广告管理 控制器")
@RestController
@RequestMapping("/ad")
public class AdController extends BaseController{

    @Resource
    private AdService adService;

    @Operation(summary = "分页查询广告管理")
    @PutMapping("/listPage")
    public Page<AdListDTO> listPage(@RequestBody @Valid AdQueryDTO queryDTO){
        return adService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出广告管理")
    public ResponseEntity<byte[]> export(@RequestBody @Valid AdQueryDTO queryDTO) {
        return adService.export(queryDTO);
    }

    @Operation(summary = "新增活更新广告管理")
    @PostMapping("/save")
    @Log("新增|修改广告管理")
    public void save(@RequestBody @Valid AdDTO adDTO){
         adService.save(adDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public AdDTO find(@PathVariable Integer id){
        return adService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除广告管理")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        adService.delete(id);
    }

    

}