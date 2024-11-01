package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;

/**
 * <p>Title: liveShop</p >
 * <p>Description: FinanceTypeEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023-02-05 14:08 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public enum FinancePayStateEnum implements BaseEnum {
    //新建
    NEW(0, "待支付"),
    ING(1, "进行中"),
    //已支付
    PAID(2, "已支付"),
    //已退款
    REFUND(3, "已退款"),
    //已取消
    CANCEL(4, "已取消"),
    //已关闭
    CLOSE(5, "已关闭"),
    ;

    private final Integer code;
    private final String name;

    FinancePayStateEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
