package com.kdao.light.common.dto.article;

import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.PageInfo;
import com.kdao.light.common.dto.SysBaseDTO;
import com.kdao.light.common.enums.FileTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: 球场</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2022-09-21 15:32:32</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends SysBaseDTO implements IDictionaryObject {
    @ApiModelProperty(value="标题" )
    private String title;

    @ApiModelProperty(value="类型" )
    private String type;

    @ApiModelProperty(value="内容" )
    private String content;

    @ApiModelProperty(value="项目id" )
    private Integer projectId;


}
