package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysLog;
import cn.light.entity.entity.SysUser;
import cn.light.entity.mapper.LogMapper;
import cn.light.entity.repository.LogRepository;
import cn.light.packet.dto.log.LogDTO;
import cn.light.packet.dto.log.LogListDTO;
import cn.light.packet.dto.log.LogQueryDTO;
import cn.light.server.service.LogService;
import cn.light.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 自动生成 日志 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:19:23
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements LogService {
    @Resource
    private  LogRepository logRepository;
    @Resource
    private UserService userService;

    @Override
    public Page<LogListDTO> listPage(LogQueryDTO queryDTO){
         Page<SysLog> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, LogListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(LogQueryDTO queryDTO) {
        List<SysLog> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Log"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, LogListDTO.class), fileName, LogListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public LogDTO save(LogDTO saveDTO) {
        String userName = null;
        //获取用户信息
        try {
            SysUser user = userService.getUserCache();
            userName = Objects.isNull(user) ? "未知用户" : user.getUsername();
        } catch (Exception ignored) {
            log.warn("获取用户信息失败");
        }
        saveDTO.setUsername(userName);
        SysLog log = DtoMapper.convert(saveDTO, SysLog.class);
        logRepository.save(log);
        return DtoMapper.convert(log, LogDTO.class);
    }

    @Override
    public void delete(Integer id) {
        logRepository.deleteById(id);
    }

    @Override
    public LogDTO find(Integer id){
        SysLog one = logRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, LogDTO.class);
    }

    


}