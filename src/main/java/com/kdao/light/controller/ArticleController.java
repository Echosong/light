package com.kdao.light.controller;

import cn.hutool.core.date.DateUtil;
import com.kdao.light.common.dto.article.ArticleDTO;
import com.kdao.light.common.dto.article.ArticleListDTO;
import com.kdao.light.common.dto.article.ArticleQueryDTO;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdArticle;
import com.kdao.light.repository.ArticleRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容相关
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "新闻文章控制器")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Operation(summary = "分页文章")
    @PutMapping("/listPage")
    public Page<ArticleListDTO> listPage(@RequestBody @Valid ArticleQueryDTO queryDTO){
        Page<KdArticle> dataPages = articleRepository.listPage( queryDTO.getRequest());
        return DtoMapper.convertPage(dataPages, ArticleListDTO.class);
    }

    @Operation(summary = "新增活更新文章")
    @PostMapping("/save")
    public void save(@RequestBody @Valid ArticleDTO articleDTO){
        KdArticle kdArticle = DtoMapper.convert(articleDTO, KdArticle.class);
        articleRepository.save(kdArticle);
    }

    @Operation(summary = "查询全部文章")
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
    public void delete(@PathVariable Integer id) {
        articleRepository.deleteById(id);
    }

}