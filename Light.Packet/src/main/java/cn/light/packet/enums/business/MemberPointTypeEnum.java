package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * <p>Title: </p >
 * <p>Description: 用户状态</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0116:16</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum MemberPointTypeEnum implements BaseEnum {
    /**
     * 用户状态
     */
    SALE(10, "消费", 1),
    REG(30, "注册", 300),
    SIGN(40, "签到", 5),
    AUTH(50, "实名认证", 100),
    PUBLIC(60, "发布游记", 30),
    COMMENT(70, "购买评论", 5),
    FOLLOW(80, "回访问", 5),
    LIKE(90, "点赞", 3),
    //打赏
    REWARD(100, "打赏", 0),
    //被打赏
    REWARDED(110, "被打赏", 0),

    LEVEL(120, "赠送会员积分", 0),
    GRANT(140, "发放会员积分", 0),
    //完善资料
    PERFECT(130, "完善资料", 300),
    //会员购买
    BUY(140, "会员购买", 0),
    //消费抵扣
    DEDUCTION(150, "消费抵扣", 0);

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }



    private final Integer code;
    private final String name;
    private final Integer point;

    MemberPointTypeEnum(Integer code, String name, Integer point) {
        this.code = code;
        this.name = name;
        this.point = point;
    }
}
