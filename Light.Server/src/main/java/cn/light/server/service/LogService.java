package  cn.light.server.service;

import cn.light.entity.entity.SysLog;
import cn.light.entity.entity.SysUser;
import cn.light.packet.dto.log.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 日志 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:19:23
 */
public interface LogService extends IService<SysLog> {

    Page<LogListDTO> listPage(LogQueryDTO queryDTO);

    ResponseEntity<byte[]> export(LogQueryDTO queryDTO);

    void save(LogDTO logDTO);

    LogDTO find(Integer id);

    void delete(Integer id);
    

}