package  #(SYS_PATH).server.service;

import #(SYS_PATH).entity.entity.#(UpTableName);
import #(SYS_PATH).packet.dto.#(EntityName).*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 #(tableInfo) service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #(localDate)
 */
public interface #(UpEntityName)Service extends IService<#(UpTableName)> {

    Page<#(UpEntityName)ListDTO> listPage(#(UpEntityName)QueryDTO queryDTO);

    ResponseEntity<byte[]> export(#(UpEntityName)QueryDTO queryDTO);

    #(UpEntityName)DTO save(#(UpEntityName)DTO #(EntityName)DTO);

    #(UpEntityName)DTO find(Integer id);

    void delete(Integer id);
    #if(isKey)
    List<Map<String, Object>> getMap();
    #end

}