package cn.light.admin.controller;

import cn.hutool.core.date.DateUtil;
import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.annotation.Log;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysArticle;
import cn.light.entity.mapper.ArticleMapper;
import cn.light.entity.repository.ArticleRepository;
import cn.light.packet.dto.article.ArticleDTO;
import cn.light.packet.dto.article.ArticleListDTO;
import cn.light.packet.dto.article.ArticleQueryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
        Page<SysArticle> dataPages  =  PageUtil.getPage(articleMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ArticleListDTO.class);
    }

    @Operation(summary = "新增活更新新闻")
    @PostMapping("/save")
    @Log("新增|修改新闻")
    public void save(@RequestBody @Valid ArticleDTO articleDTO){
        SysArticle kdArticle = DtoMapper.convert(articleDTO, SysArticle.class);
        articleRepository.save(kdArticle);
    }

    @Operation(summary = "查询全部新闻")
    @GetMapping("/list")
    public List<ArticleListDTO> list(){
        List<SysArticle> all = articleRepository.findAll();
        return DtoMapper.convertList(all, ArticleListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ArticleDTO find(@PathVariable Integer id){
        SysArticle one = articleRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ArticleDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除新闻")
    public void delete(@PathVariable Integer id) {
        articleRepository.deleteById(id);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出新闻")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ArticleQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysArticle> all = articleMapper.listPage(queryDTO);
        String fileName = "article"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ArticleListDTO.class), fileName, ArticleListDTO.class);
    }

}