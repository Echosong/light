package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysArticle;
import cn.light.entity.mapper.ArticleMapper;
import cn.light.packet.dto.article.ArticleDTO;
import cn.light.packet.dto.article.ArticleListDTO;
import cn.light.packet.dto.article.ArticleQueryDTO;
import cn.light.server.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 自动生成 新闻 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 20:36:06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {


    @Override
    public Page<ArticleListDTO> listPage(ArticleQueryDTO queryDTO){
         Page<SysArticle> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, ArticleListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(ArticleQueryDTO queryDTO) {
        List<SysArticle> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Article"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ArticleListDTO.class), fileName, ArticleListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public ArticleDTO save(ArticleDTO saveDTO) {
        SysArticle article = DtoMapper.convert(saveDTO, SysArticle.class);
        this.saveOrUpdate(article);
        return DtoMapper.convert(article, ArticleDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public ArticleDTO find(Integer id){
        SysArticle one = this.getOptById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ArticleDTO.class);
    }

    
    @Override
    public List<Map<String, Object>> getMap(){
         List<SysArticle> all = this.baseMapper.selectList(new LambdaQueryWrapper<SysArticle>()
                        .select(SysArticle::getId, SysArticle::getTitle)
                        .orderByDesc(SysArticle::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysArticle item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getTitle());
            maps.add(map);
        }
        return maps;
    }
    


}