package cn.light.entity.entity;


import cn.light.common.anno.*;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.UserRegTypeEnum;
import cn.light.packet.enums.UserSexEnum;
import cn.light.packet.enums.UserStateEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:29</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户")
public class SysUser extends  SysBase implements Serializable {

    @NotNull
    @AutoEntityField(value = "账号", isKeyName = true)
    @Pattern(regexp ="\\d{11}", message = "必须为手机格式")
    @InQueryDTO
    private String username;

    @Column(length = 300, columnDefinition = "密码")
    @Length(min = 6, message = "密码必须大于等于6位")
    @AutoEntityField("密码")
    @NotinListDTO
    private String password;

    @AutoEntityField(value = "性别", enums = UserSexEnum.class)
    @Range(min = 0, max = 1)
    @AutoSorted
    private Integer sex;

    @AutoEntityField("工号")
    private String code;

    @AutoEntityField("姓名")
    @Length(min = 2, message = "姓名必须大于两个字符")
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "状态", notes = "0,1", enums = UserStateEnum.class, htmlType = HtmlTypeEnum.RADIO)
    private Integer state = 0 ;

    @Email
    @AutoEntityField("邮箱")
    private String email;

    @AutoEntityField("注册ip")
    private String regIp;

    @AutoEntityField("登录ip")
    private String loginIp;

    @AutoEntityField(value = "个人简介", htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String info;

}
