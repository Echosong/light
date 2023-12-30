package com.kdao.light.entity;


import com.kdao.autocode.anno.*;
import com.kdao.light.common.enums.HtmlTypeEnum;
import com.kdao.light.common.enums.UserRegTypeEnum;
import com.kdao.light.common.enums.UserSexEnum;
import com.kdao.light.common.enums.UserStateEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class KdUser extends  SysBase implements Serializable {

    @AutoEntityField("账号")
    @NotNull
    @Pattern(regexp ="\\d{11}", message = "必须为手机格式")
    @InQueryDTO
    private String username;

    @Length(min = 6, message = "密码必须大于等于6位")
    @AutoEntityField("密码")
    @NotinListDTO
    private String password;

    @AutoEntityField(value = "性别", enums = UserSexEnum.class)
    @Range(min = 0, max = 1)
    @AutoSorted
    private Integer sex;

    @AutoEntityField("身高")
    @Range(min = 0, max = 300)
    private Double height;

    @AutoEntityField("体重")
    private Double weight;

    @AutoEntityField("昵称")
    private String nick;

    @AutoEntityField(value = "注册方式", notes = "0,1,2", enums = UserRegTypeEnum.class)
    private Integer type;


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

    @AutoEntityField(value = "身份证正面", htmlType = HtmlTypeEnum.UPLOAD)
    @NotinListDTO
    private String frontCard;

    @NotinListDTO
    @AutoEntityField(value = "身份证反面", htmlType = HtmlTypeEnum.UPLOAD)
    private String reverseCard;

    @AutoEntityField(value = "个人简介", htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String info;

}
