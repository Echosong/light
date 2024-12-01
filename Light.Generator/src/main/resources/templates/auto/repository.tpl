package #(PackageName).repository;
import #(SYS_PATH).entity.entity.#(UpTableName);
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* #(UpEntityName)Repository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : #(localDate)
*/
@Repository
public interface #(UpEntityName)Repository extends JpaRepository<#(UpTableName), Integer> {

}