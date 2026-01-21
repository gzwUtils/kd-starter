package org.action.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 23:55
 */
@MapperScan("org.action.user.mapper")   // 关键
@ComponentScan(basePackages = "org.action")
@SpringBootApplication
@EnableDubbo
public class KdUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(KdUserApplication.class, args);
    }
}
