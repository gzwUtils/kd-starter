package org.action.notice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gzw
 * @description：
 * @since：2024/8/3 01:47
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = "org.action.notice")
public class NoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

}
