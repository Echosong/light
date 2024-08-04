package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.SysMessageTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* MessageTemplateRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-08-04 22:49:58
*/
@Repository
@SuppressWarnings("ALL")
public interface MessageTemplateRepository extends JpaRepository<SysMessageTemplate, Integer> {

}