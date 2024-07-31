package org.action.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * @author gzw
 * @description： 缓存配置
 * @since：2024/7/30 22:13
 */
@Configuration
@EnableMethodCache(basePackages = "org.action")
public class CacheConfiguration {

}
