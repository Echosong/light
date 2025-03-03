package cn.light.admin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Nobita
 * @date 2020/4/18 12:10 下午
 */
@Data
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String accessPathPattern = "/u/**";
    private String uploadFolder;
    private String accessPrefixUrl = "/u/";
    private String url;
}
