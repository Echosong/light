package cn.light.admin.controller;
import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.annotation.Log;
import cn.light.common.annotation.NoPermission;
import cn.light.common.util.ExcelUtil;
import cn.light.packet.dto.article.ArticleDTO;
import cn.light.packet.dto.article.ArticleListDTO;
import cn.light.packet.dto.article.ArticleQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.KdArticle;
import cn.light.entity.mapper.ArticleMapper;
import cn.light.entity.repository.ArticleRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 新闻管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 20:15:44</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "新闻 控制器")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{
    @Resource
    private  ArticleRepository articleRepository;

    @Resource
    private ArticleMapper articleMapper;

    @Operation(summary = "分页查询新闻")
    @PutMapping("/listPage")
    public Page<ArticleListDTO> listPage(@RequestBody @Valid ArticleQueryDTO queryDTO){
        Page<KdArticle> dataPages  =  PageUtil.getPage(articleMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ArticleListDTO.class);
    }

    @Operation(summary = "新增活更新新闻")
    @PostMapping("/save")
    @Log("新增|修改新闻")
    public void save(@RequestBody @Valid ArticleDTO articleDTO){
        KdArticle kdArticle = DtoMapper.convert(articleDTO, KdArticle.class);
        articleRepository.save(kdArticle);
    }

    @Operation(summary = "查询全部新闻")
    @GetMapping("/list")
    public List<ArticleListDTO> list(){
        List<KdArticle> all = articleRepository.findAll();
        return DtoMapper.convertList(all, ArticleListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ArticleDTO find(@PathVariable Integer id){
        KdArticle one = articleRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ArticleDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除新闻")
    public void delete(@PathVariable Integer id) {
        articleRepository.deleteById(id);
    }

    @GetMapping("/export")
    @NoPermission
    @ApiResultIgnore
    @Log("导出新闻")
    public ResponseEntity<byte[]> test() throws IOException, IllegalAccessException {
        return ExcelUtil.generateImportFile(new LinkedList(), "test.xlsx", ArticleListDTO.class);
    }

}