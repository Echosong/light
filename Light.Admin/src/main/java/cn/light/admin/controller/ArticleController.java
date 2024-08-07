package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.article.ArticleDTO;
import cn.light.packet.dto.article.ArticleListDTO;
import cn.light.packet.dto.article.ArticleQueryDTO;
import cn.light.server.service.ArticleService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  新闻 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-24 20:36:06
 */
@Tag(name = "新闻 控制器")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

    @Resource
    private ArticleService articleService;

    @Operation(summary = "分页查询新闻")
    @PutMapping("/listPage")
    public Page<ArticleListDTO> listPage(@RequestBody @Valid ArticleQueryDTO queryDTO){
        return articleService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出新闻")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ArticleQueryDTO queryDTO) {
        return articleService.export(queryDTO);
    }

    @Operation(summary = "新增活更新新闻")
    @PostMapping("/save")
    @Log("新增|修改新闻")
    public void save(@RequestBody @Valid ArticleDTO articleDTO){
         articleService.save(articleDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public ArticleDTO find(@PathVariable Integer id){
        return articleService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除新闻")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        articleService.delete(id);
    }

    
    @Operation(summary = "简单查询新闻")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         return articleService.getMap();
    }
    

}