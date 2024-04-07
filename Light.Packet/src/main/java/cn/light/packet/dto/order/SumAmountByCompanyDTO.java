package cn.light.packet.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>Title: insure-ground</p >
 * <p>Description: SumAmountByCompanyDTO</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/4/7 10:51 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
public class SumAmountByCompanyDTO {
    private Integer channelId;

    private String channelName;

    private BigDecimal amount;
}
