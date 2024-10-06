package  cn.light.server.service;

import cn.light.entity.entity.SysFeedback;
import cn.light.packet.dto.feedback.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 意见反馈 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:25:40
 */
public interface FeedbackService extends IService<SysFeedback> {

    Page<FeedbackListDTO> listPage(FeedbackQueryDTO queryDTO);

    ResponseEntity<byte[]> export(FeedbackQueryDTO queryDTO);

    FeedbackDTO save(FeedbackDTO feedbackDTO);

    FeedbackDTO find(Integer id);

    void delete(Integer id);
    

}