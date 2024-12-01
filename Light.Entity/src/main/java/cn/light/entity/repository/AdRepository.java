package cn.light.entity.repository;
import cn.light.entity.entity.SysAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* AdRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-12-01 16:24:23
*/
@Repository
public interface AdRepository extends JpaRepository<SysAd, Integer> {

}