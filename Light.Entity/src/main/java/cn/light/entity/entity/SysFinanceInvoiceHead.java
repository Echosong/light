package cn.light.entity.entity;

import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.business.InvoiceCategoryEnum;
import cn.light.packet.enums.business.InvoiceStateEnum;
import cn.light.packet.enums.business.InvoiceTypeEnum;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 发票表
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class SysFinanceInvoiceHead extends SysBase {
    @AutoEntityField(value = "会员ID")
    private Integer memberId;

    @AutoEntityField(value = "会员名")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "发票号")
    @InQueryDTO
    private String no;

    @AutoEntityField(value = "发票抬头")
    @InQueryDTO
    private String title;

    @AutoEntityField(value = "发票类型", enums = InvoiceTypeEnum.class)
    private String type;

    @AutoEntityField(value = "发票分类", enums = InvoiceCategoryEnum.class)
    private Integer category;

    @AutoEntityField(value = "发票状态", enums = InvoiceStateEnum.class)
    private Integer state;

    // 发票日期
    @AutoEntityField(value = "发票日期")
    private Date date;

    @AutoEntityField(value = "发票邮箱")
    private String email;
    //开户行
    @AutoEntityField(value = "开户行")
    private String bank;

    // 银行账号
    @AutoEntityField(value = "银行账号")
    private String bankAccount;

    //企业地址
    @AutoEntityField(value = "企业地址")
    private String address;

    //企业电话
    @AutoEntityField(value = "企业电话")
    private String phone;

    // 发票备注
    @AutoEntityField(value = "发票备注", htmlType = HtmlTypeEnum.TEXTAREA)
    private String remark;

    //是否默认
    @AutoEntityField(value = "是否默认")
    @AutoEntityFieldDefault("0")
    private Integer isDefault;

}
