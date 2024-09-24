package cn.light.entity.entity;

import cn.light.common.anno.*;
import cn.light.packet.enums.system.UserRegTypeEnum;
import cn.light.packet.enums.system.YesOrNoEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统前端客户
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "系统前端客户")
@RedisHash
public class SdMember extends SysBase {

    /** 用户账户(跟accout一样) */
    @AutoEntityField(value = "用户账户", notes = "用户账户(跟accout一样)")
    @InQueryDTO
    private String username;


    /** 用户密码（跟pwd） */
    @AutoEntityField(value = "用户密码（跟pwd）")
    @NotinListDTO
    private String password;



    /** 真实姓名 */
    @AutoEntityField(value = "真实姓名")
    @InQueryDTO
    private String realName;


    /** 生日 */
    @AutoEntityField(value = "生日",notes = "格式：01-01 月-日")
    private String birthday;


    /** 身份证号码 */
    @AutoEntityField(value = "身份证号码")
    @Pattern(regexp = "\\d{18}",message = "身份证号码格式错误")
    private String cardNo;


    /** 用户备注 */
    @AutoEntityField(value = "用户备注")
    private String mark;



    /** 用户分组id */
    @AutoEntityField(value = "用户分组id")
    private Integer groupId;


    /** 用户昵称 */
    @AutoEntityField(value = "用户昵称")
    private String nickName;


    /** 用户头像 */
    @AutoEntityField(value = "用户头像")
    private String avatar;


    /** 手机号码 */
    @AutoEntityField(value = "手机号码")
    @Pattern(regexp = "\\d{11}",message = "手机号码格式错误")
    @InQueryDTO
    private String phone;

    @AutoEntityField(value = "性别", enums = YesOrNoEnum.class)
    private Integer sex;

    /** 添加ip */
    @AutoEntityField(value = "添加ip")
    private String addIp;


    /** 最后一次登录ip */
    @AutoEntityField(value = "最后一次登录ip")
    private String lastIp;


    /** 用户余额 */
    @AutoEntityField(value = "用户余额")
    @Min(0)
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal balance;


    /** 佣金金额 */
    @AutoEntityField(value = "总佣金金额")
    @Min(0)
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal brokeragePrice;

    @Min(0)
    @AutoEntityField(value = "冻结佣金")
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal freeze;

    @Min(0)
    @AutoEntityField(value = "提现金")
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal cash;

    /** 用户剩余积分 */
    @AutoEntityField(value = "积分")
    @Min(0)
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal point;

    @AutoEntityField(value = "累计积分")
    @AutoEntityFieldDefault(value = "0")
    private  BigDecimal allPoint;

    @AutoEntityField(value = "累计消费金额")
    @Min(0)
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal consume;


    /** 1为正常，0为禁止 */
    @AutoEntityField(value = "状态", notes = "20为正常，10为禁止")
    private Integer status;

    //注销原因
    @AutoEntityField(value = "注销原因")
    private String logoutReason;

    /** 等级 */
    @AutoEntityField(value = "等级")
    private Integer level;

    /** 推广元id */
    @AutoEntityField(value = "推荐人")
    private Integer parentId;

    @AutoEntityField(value = "推荐人加入时间")
    private Date  parentTime;

    /** 用户类型 */
    @AutoEntityField(value = "用户类型")
    private String userType;

    /** 下级人数 */
    @AutoEntityField(value = "下级人数")
    private Long spreadCount;

    @AutoEntityField(value = "喜好", notes = "多个可以用逗号隔开",len = 1000)
    private String likes;

    /** 详细地址 */
    @AutoEntityField(value = "详细地址")
    private String address;

    @AutoEntityField(value = "地区")
    private String region;

    /** 用户登陆类型，h5,wech at,routine */
    @AutoEntityField(value = "用户登陆类型，h5,wechat,routine", enums = UserRegTypeEnum.class)
    private Integer loginType;


    //会员到期时间
    @AutoEntityField(value = "会员到期时间")
    private Date memberExpireTime;


    @AutoEntityField(value = "推送客户端id")
    @NotinListDTO
    private String pushClientId;

    @AutoEntityField(value = "地理位置")
    private String geo;

    //身份证正面
    @AutoEntityField(value = "身份证正面")
    private String cardFront;

    //身份证反面
    @AutoEntityField(value = "身份证反面")
    private String cardBack;

    @AutoEntityField(value = "是否认证",enums = YesOrNoEnum.class)
    @AutoEntityFieldDefault(value = "0")
    private Integer isAuth;

    @AutoEntityField(value = "微信")
    private String openId;

    @AutoEntityField(value = "qq")
    private String qq;

    //关注
    @AutoEntityField(value = "关注")
    @AutoEntityFieldDefault("0")
    private Integer followCount;

    //资料是否完善
    @AutoEntityField(value = "资料是否完善",enums = YesOrNoEnum.class)
    @AutoEntityFieldDefault(value = "0")
    private Integer isPerfect;

    //城市id
    @AutoEntityField(value = "城市id")
    private Integer cityId;

    //fastOpenId
    @AutoEntityField(value = "快捷登录openId")
    private String fastOpenId;
}
