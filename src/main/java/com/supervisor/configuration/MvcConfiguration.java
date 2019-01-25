package com.supervisor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/libraries/**")
                .addResourceLocations("/resources/libraries/").setCachePeriod(3600)
                .resourceChain(true).addResolver(new PathResourceResolver());
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/").setCachePeriod(3600)
                .resourceChain(true).addResolver(new PathResourceResolver());
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/").setCachePeriod(3600)
                .resourceChain(true).addResolver(new PathResourceResolver());
    }
}
