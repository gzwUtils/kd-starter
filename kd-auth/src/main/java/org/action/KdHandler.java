package org.action;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gzw
 * @description：
 * @since：2024/4/24 02:05
 */
@EnableDubbo
@SpringBootApplication
public class KdHandler {

    public static void main(String[] args) {
        SpringApplication.run(KdHandler.class,args);
    }
}
