package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 优惠券类型
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum OrderStateEnum implements BaseEnum {
    //待付款
    WAIT_PAY(10, "待付款"),
    //拼团中
    PAID(11, "拼团中"),
    //待发货
    WAIT_SEND(20, "待发货"),
    //待收货
    WAIT_RECEIVE(30, "待收货"),
    //待评价
    WAIT_COMMENT(40, "待评价"),
    //已完成
    FINISHED(50, "已完成"),
    //已取消
    CANCELLED(60, "已关闭"),
    //已退款
    REFUND(70, "已退款"),
    //已删除
    DELETED(80, "已删除");


    private final Integer code;
    private final String name;

    OrderStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
