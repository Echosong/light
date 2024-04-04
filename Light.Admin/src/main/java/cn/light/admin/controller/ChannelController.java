package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.packet.dto.channel.ChannelDTO;
import cn.light.packet.dto.channel.ChannelListDTO;
import cn.light.packet.dto.channel.ChannelQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysChannel;
import cn.light.entity.mapper.ChannelMapper;
import cn.light.entity.repository.ChannelRepository;
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
 * <p>Description: 渠道信息表管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-04 10:56:56</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "渠道信息表 控制器")
@RestController
@RequestMapping("/channel")
public class ChannelController extends BaseController{
    @Resource
    private  ChannelRepository channelRepository;

    @Resource
    private ChannelMapper channelMapper;

    @Operation(summary = "分页查询渠道信息表")
    @PutMapping("/listPage")
    public Page<ChannelListDTO> listPage(@RequestBody @Valid ChannelQueryDTO queryDTO){
        Page<SysChannel> dataPages  =  PageUtil.getPage(channelMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ChannelListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出渠道信息表")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ChannelQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysChannel> all = channelMapper.listPage(queryDTO);
        String fileName = "Channel"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ChannelListDTO.class), fileName, ChannelListDTO.class);
    }

    @Operation(summary = "新增活更新渠道信息表")
    @PostMapping("/save")
    @Log("新增|修改渠道信息表")
    public void save(@RequestBody @Valid ChannelDTO channelDTO){
        SysChannel sysChannel = DtoMapper.convert(channelDTO, SysChannel.class);
        channelRepository.save(sysChannel);
    }

    @Operation(summary = "查询全部渠道信息表")
    @GetMapping("/list")
    public List<ChannelListDTO> list(){
        List<SysChannel> all = channelRepository.findAll();
        return DtoMapper.convertList(all, ChannelListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ChannelDTO find(@PathVariable Integer id){
        SysChannel one = channelRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ChannelDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除渠道信息表")
    public void delete(@PathVariable Integer id) {
        channelRepository.deleteById(id);
    }

    

}