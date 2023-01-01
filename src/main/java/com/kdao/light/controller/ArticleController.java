package com.kdao.light.controller;

import com.kdao.light.common.dto.article.ArticleDTO;
import com.kdao.light.common.dto.article.ArticleListDTO;
import com.kdao.light.common.dto.article.ArticleQueryDTO;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdArticle;
import com.kdao.light.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: 球场管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2022-09-21 15:32:32</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Api(tags = "新闻 控制器")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @ApiOperation("分页查询球场")
    @PutMapping("/listPage")
    public Page<ArticleListDTO> listPage(@RequestBody @Valid ArticleQueryDTO queryDTO){
        Page<KdArticle> dataPages = articleRepository.listPage( queryDTO.getRequest());
        return DtoMapper.convertPage(dataPages, ArticleListDTO.class);
    }

    @ApiOperation("新增活更新球场")
    @PostMapping("/save")
    public void save(@RequestBody @Valid ArticleDTO articleDTO){
        KdArticle kdArticle = DtoMapper.convert(articleDTO, KdArticle.class);
        articleRepository.save(kdArticle);
    }

    @ApiOperation("查询全部球场")
    @GetMapping("/list")
    public List<ArticleListDTO> list(){
        List<KdArticle> all = articleRepository.findAll();
        return DtoMapper.convertList(all, ArticleListDTO.class);
    }

    @ApiOperation("查询")
    @GetMapping("/find/{id}")
    public ArticleDTO find(@PathVariable Integer id){
        KdArticle one = articleRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ArticleDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除")
    public void delete(@PathVariable Integer id) {
        articleRepository.deleteById(id);
    }

}