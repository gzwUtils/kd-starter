package org.action.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gzw
 * @description：
 * @since：2024/8/1 23:57
 */
@Data
@ConfigurationProperties(prefix = "spring.xxl.job")
public class XxlJobProperties {


    private boolean enabled;

    private String adminAddresses;

    private String accessToken;

    private String executorAppName;

    private String executorIp;

    private int executorPort;

    private int executorLogRetentionDays;

    private String executorLogPath;

}
