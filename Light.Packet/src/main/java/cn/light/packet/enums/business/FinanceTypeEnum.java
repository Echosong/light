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
public enum FinanceTypeEnum implements BaseEnum {
    /**
     * 佣金账户余额变动
     */
    CASH(0,"提现扣除"),
    SALE(1, "销售佣金"),
    SHARE(2,"推广佣金"),
    RECHARGE(3,"会员推广佣金");

    private final Integer code;
    private final String name;

    FinanceTypeEnum(Integer code, String name){
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
