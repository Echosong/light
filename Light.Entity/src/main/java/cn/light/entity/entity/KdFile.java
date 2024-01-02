package cn.light.entity.entity;


import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.FileTypeEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002922:06</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "文件管理")
public class KdFile extends SysBase {

    @AutoEntityField(value = "图标",htmlType = HtmlTypeEnum.UPLOAD)
    private String ico;

    @NotNull
    @AutoEntityField(value = "文件名", htmlType = HtmlTypeEnum.FILE)
    @InQueryDTO
    private String fileName;

    @AutoEntityField("文件扩展名")
    private String extend;

    @AutoEntityField("文件存储路径")
    private String filePath;

    @AutoEntityField(value = "文件类型",enums = FileTypeEnum.class)
    @InQueryDTO
    private Integer fileType;

    @AutoEntityField("文件大小")
    private long fileSize;

    @AutoEntityField("相对路径")
    private String urlPath;

    @AutoEntityField("文件唯一标识")
    private String uuid;
}
