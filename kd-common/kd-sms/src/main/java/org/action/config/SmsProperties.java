package org.action.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 15:23
 */
@Data
@ConfigurationProperties(prefix = "spring.sms")
public class SmsProperties {

    private String account;

    private String smsPassword;

    private String host;

}
