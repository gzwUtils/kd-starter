package org.action;

import cn.hutool.core.thread.ThreadUtil;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gzw
 * @description：
 * @since：2024/4/24 00:32
 */

@Configuration
public class KdThreadPoolAutoConfiguration {

    @Bean
    @ConditionalOnClass(ThreadPoolExecutor.class)
    public ThreadPoolExecutor kdThreadPool(){
        return new ThreadPoolExecutor(10,20,200,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(100), ThreadUtil.newNamedThreadFactory("kd-starter-threadPool-", true),new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
