package cn.light.packet.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3122:56</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class UserCacheDTO  implements Serializable {
   @Schema(defaultValue = "用户id")
    private Integer id;
   @Schema(defaultValue = "用户姓名")
    private String name;
}
