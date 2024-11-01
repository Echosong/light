package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysFeedback;
import cn.light.entity.mapper.FeedbackMapper;
import cn.light.server.service.FeedbackService;
import cn.light.packet.dto.feedback.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 意见反馈 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:25:40
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, SysFeedback> implements FeedbackService {

    @Override
    public Page<FeedbackListDTO> listPage(FeedbackQueryDTO queryDTO){
         Page<SysFeedback> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, FeedbackListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(FeedbackQueryDTO queryDTO) {
        List<SysFeedback> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Feedback"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, FeedbackListDTO.class), fileName, FeedbackListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public FeedbackDTO save(FeedbackDTO saveDTO) {
        SysFeedback feedback = DtoMapper.convert(saveDTO, SysFeedback.class);
        this.saveOrUpdate(feedback);
        return DtoMapper.convert(feedback, FeedbackDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public FeedbackDTO find(Integer id){
        SysFeedback one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, FeedbackDTO.class);
    }

    


}