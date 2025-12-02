package org.action.es.config;


import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EsMapperScan("org.action.es.*.es.mapper")
@ConditionalOnProperty(value = "easy-es.enable", havingValue = "true")
public class EsConfiguration {
}
