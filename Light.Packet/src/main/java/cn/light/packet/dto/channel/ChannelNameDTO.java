package cn.light.packet.dto.channel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前端下来使用
 * @author Administrator
 */
@Getter
@Setter
public class ChannelNameDTO {
    //渠道名
    private String channelName;
    //公司名称
    private List<CompanyNameDTO> companyNames;

    //定义个内部类
    @Getter
    @Setter
    public static class CompanyNameDTO {
        //公司名称
        private String companyName;
        //方案
        private List<String> schemes;
    }

}
