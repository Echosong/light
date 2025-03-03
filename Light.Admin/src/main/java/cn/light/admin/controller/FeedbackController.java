package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.feedback.FeedbackDTO;
import cn.light.packet.dto.feedback.FeedbackListDTO;
import cn.light.packet.dto.feedback.FeedbackQueryDTO;
import cn.light.server.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  意见反馈 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-10-05 21:25:40
 */
@Tag(name = "意见反馈 控制器")
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController{

    @Resource
    private FeedbackService feedbackService;

    @Operation(summary = "分页查询意见反馈")
    @PutMapping("/listPage")
    public Page<FeedbackListDTO> listPage(@RequestBody @Valid FeedbackQueryDTO queryDTO){
        return feedbackService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出意见反馈")
    public ResponseEntity<byte[]> export(@RequestBody @Valid FeedbackQueryDTO queryDTO) {
        return feedbackService.export(queryDTO);
    }

    @Operation(summary = "新增活更新意见反馈")
    @PostMapping("/save")
    @Log("新增|修改意见反馈")
    public void save(@RequestBody @Valid FeedbackDTO feedbackDTO){
         feedbackService.save(feedbackDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public FeedbackDTO find(@PathVariable Integer id){
        return feedbackService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除意见反馈")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        feedbackService.delete(id);
    }

    

}