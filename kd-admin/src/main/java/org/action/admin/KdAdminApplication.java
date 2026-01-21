package org.action.admin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.action.admin"})
@EnableDubbo
public class KdAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdAdminApplication.class, args);
    }
}
