package cn.light.packet.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>Title: insure-ground</p >
 * <p>Description: OrderStatisticsVO</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/4/7 9:32 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
public class OrderStatisticsDTO {
    //总人数
    @Schema(title = "总人数")
    private Integer peopleCount;

    //三类人数
    @Schema(title = "三类人数")
    private Integer threeClassNum;

    //四类人数

    @Schema(title = "四类人数")
    private Integer fourClassNum;

    //五类人数
    @Schema(title = "五类人数")
    private Integer fiveClassNum;

    //三类总业绩
    @Schema(title = "三类总业绩")
    private BigDecimal threeClassTotal;

    //四类总业绩
    @Schema(title = "四类总业绩")
    private BigDecimal fourClassTotal;

    //五类总业绩
    @Schema(title = "五类总业绩")
    private BigDecimal fiveClassTotal;

    //总返利
    @Schema(title = "总返利")
    private BigDecimal totalRebate;

    //总利润
    @Schema(title = "总利润")
    private BigDecimal totalProfit;
}
