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
public enum FinancePayTypeEnum implements BaseEnum {
    //活动定金
    ACTIVITY(10,"活动全款"),
    //商品
    GOODS(20,"商品"),
    //其他
    MEMBER(30,"会员购买"),
    //活动定制
    CUSTOM(40,"活动定制定金"),
    //定制尾款
    CUSTOM_TAIL(41,"活动定制尾款"),
    //活动购买尾款
    ACTIVITY_TAIL(61,"活动购买尾款"),
    //活动定金
    ACTIVITY_FIRST(60,"活动定金"),
    //B端会员购买
    B_MEMBER(70,"B端会员购买"),
    //其他
    OTHER(50,"其他");

    private final Integer code;
    private final String name;

    FinancePayTypeEnum(Integer code, String name){
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
