package  #{SYS_PATH}.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import #{SYS_PATH}.entity.entity.#{UpTableName}#;
import #{SYS_PATH}.entity.mapper.#{UpEntityName}#Mapper;
import #{SYS_PATH}.entity.repository.#{UpEntityName}#Repository;
import #{SYS_PATH}.server.service.#{UpEntityName}#Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 #{tableInfo}# service 实现
 * @author : 二胡子
 * @date : #{localDate}#
 */
@Service
public class #{UpEntityName}#ServiceImpl extends ServiceImpl<#{UpEntityName}#Mapper, #{UpTableName}#> implements #{UpEntityName}#Service {
        @Resource
        private  #{UpEntityName}#Repository #{EntityName}#Repository;

}