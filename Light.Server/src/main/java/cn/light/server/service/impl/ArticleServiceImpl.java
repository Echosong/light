package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysArticle;
import cn.light.entity.mapper.ArticleMapper;
import cn.light.entity.repository.ArticleRepository;
import cn.light.server.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 新闻 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {
        @Resource
        private  ArticleRepository articleRepository;

}