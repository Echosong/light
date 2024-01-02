package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.article.ArticleQueryDTO;
import cn.light.entity.entity.KdArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 新闻
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<KdArticle> {
    List<KdArticle> listPage(ArticleQueryDTO articleQueryDTO);
}
