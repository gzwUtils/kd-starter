package org.action.notice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gzw
 * @description：
 * @since：2024/8/3 01:47
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.action")
@EnableDubbo
public class NoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

}
