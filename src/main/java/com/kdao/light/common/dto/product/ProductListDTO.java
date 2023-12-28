package com.kdao.light.common.dto.product;

import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.PageInfo;
import com.kdao.light.common.dto.SysBaseDTO;
import com.kdao.light.common.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;



/**
 * <p>Title:产品信息 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2023-12-28 21:51:20</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(name="产品名称" )
    private String name;

   @Schema(name="价格" )
    private BigDecimal price;


}
