package com.supervisor.configuration;

import com.supervisor.bean.DataIntegrityViolationExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfiguration {

    @Bean
    public DataIntegrityViolationExceptionTranslator getTranslator() {
        return new DataIntegrityViolationExceptionTranslator();
    }
}
