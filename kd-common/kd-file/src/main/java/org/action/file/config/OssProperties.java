package org.action.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class OssProperties {

    public static final String PREFIX = "spring.oss";

    private String bucket;

    private String endPoint;

    private String accessKey;

    private String accessSecret;
}
