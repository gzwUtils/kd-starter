package org.action.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 23:55
 */

@SpringBootApplication
@EnableDubbo
public class KdUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(KdUserApplication.class, args);
    }
}
