package com.supervisor.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static <T> T getBean(Class<T> beanClass) {
        return CONTEXT.getBean(beanClass);
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return CONTEXT.getBean(beanName, beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
}
