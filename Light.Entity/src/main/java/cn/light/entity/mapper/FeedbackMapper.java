package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.feedback.FeedbackQueryDTO;
import cn.light.entity.entity.SysFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 意见反馈
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:25:40
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<SysFeedback> {
    List<SysFeedback> listPage(FeedbackQueryDTO feedbackQueryDTO);
}
