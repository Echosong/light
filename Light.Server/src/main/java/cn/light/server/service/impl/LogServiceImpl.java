package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysLog;
import cn.light.entity.entity.SysUser;
import cn.light.entity.mapper.LogMapper;
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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private UserService userService;

    //全局专门线程来处理日志
    private static final ExecutorService LOG_EXECUTOR = Executors.newSingleThreadExecutor();

    @Override
    public Page<LogListDTO> listPage(LogQueryDTO queryDTO) {
        Page<SysLog> dataPages = PageUtil.getPage(this.baseMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, LogListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(LogQueryDTO queryDTO) {
        List<SysLog> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Log" + DateUtil.format(new Date(), "yyyyMMddHHmm") + ".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, LogListDTO.class), fileName, LogListDTO.class);
        } catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public void save(LogDTO saveDTO) {
        String userName;
        //获取用户信息
        try {
            SysUser user = userService.getUserCache();
            userName = Objects.isNull(user) ? "未知用户" : user.getUsername();
        } catch (Exception ignored) {
            //正则提取 username: (.*?)
            String regex = "\"username\":\"([^\"]*)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(saveDTO.getParams());
            if (matcher.find()) {
                userName = matcher.group(1); // group(1)获取第一个括号内的内容
            }else{
                userName = "未知用户";
            }
        }
        saveDTO.setUsername(userName);

        CompletableFuture.runAsync(()-> {
            SysLog log = DtoMapper.convert(saveDTO, SysLog.class);
            this.saveOrUpdate(log);
        }, LOG_EXECUTOR);

    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public LogDTO find(Integer id) {
        SysLog one = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, LogDTO.class);
    }


}