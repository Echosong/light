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
public enum ProductRuleEnum implements BaseEnum {

    UNIFY(1, "统一规格"),
    MULTIPLE(2, "多规格");


    private final Integer code;
    private final String name;

    ProductRuleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
