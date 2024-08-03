package org.action.config;

import javax.annotation.Resource;
import org.action.SmsService;
import org.action.SmsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 15:37
 */
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
public class SmsConfiguration {

    @Resource
    private SmsProperties smsProperties;

    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService() {
        SmsServiceImpl smsService = new SmsServiceImpl();
        smsService.setSmsPassword(smsProperties.getSmsPassword());
        smsService.setAccount(smsProperties.getAccount());
        smsService.setHost(smsProperties.getHost());
        return smsService;
    }



}
