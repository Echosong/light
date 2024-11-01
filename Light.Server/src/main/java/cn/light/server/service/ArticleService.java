package  cn.light.server.service;

import cn.light.entity.entity.SysArticle;
import cn.light.entity.repository.ArticleRepository;
import cn.light.packet.dto.article.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 新闻 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 20:36:06
 */
public interface ArticleService extends IService<SysArticle> {

    Page<ArticleListDTO> listPage(ArticleQueryDTO queryDTO);

    ResponseEntity<byte[]> export(ArticleQueryDTO queryDTO);

    ArticleDTO save(ArticleDTO articleDTO);

    ArticleDTO find(Integer id);

    void delete(Integer id);
    
    List<Map<String, Object>> getMap();

    ArticleRepository baseRepository();
    

}