package cn.light.packet.dto.order;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
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
 * <p>Title:业绩数据 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-06 11:14:54</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryDTO extends PageInfo {
   @Schema(title="业绩归属" )
    private Date startOwner;

   @Schema(title="业绩归属" )
    private Date endOwner;

   @Schema(title="客户名称" )
    private String customerName;

   @Schema(title="渠道名称" )
    private String channelName;

   @Schema(title="运营人" )
    private Integer operation;


}
