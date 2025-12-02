package org.action.file.config;

import org.action.file.FileService;
import org.action.file.MockFileServiceImpl;
import org.action.file.OssServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfiguration {


    private final OssProperties properties;

    public OssConfiguration(OssProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    @Profile({"default", "prod"})
    public FileService ossService() {
        OssServiceImpl ossService = new OssServiceImpl();
        ossService.setBucket(properties.getBucket());
        ossService.setEndPoint(properties.getEndPoint());
        ossService.setAccessKey(properties.getAccessKey());
        ossService.setAccessSecret(properties.getAccessSecret());
        return ossService;
    }

    @Bean
    @ConditionalOnMissingBean
    @Profile({"dev", "test"})
    public FileService mockFileService() {
        return new MockFileServiceImpl();
    }
}
