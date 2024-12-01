package cn.light.entity.repository;
import cn.light.entity.entity.SysAreaDj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* AreaDjRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-12-01 14:34:59
*/
@Repository
public interface AreaDjRepository extends JpaRepository<SysAreaDj, Integer> {

}