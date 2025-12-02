package org.action.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author gzw
 * @create 2024/9/30 16:16
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static Object getBean(String name) {
        return ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> name) {
        return ctx.getBean(name);
    }
}
