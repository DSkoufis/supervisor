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

import static com.supervisor.util.constant.ViewMapping.STATIC_CSS_ROOT;
import static com.supervisor.util.constant.ViewMapping.STATIC_JS_ROOT;
import static com.supervisor.util.constant.ViewMapping.STATIC_LIBRARIES_ROOT;
import static com.supervisor.util.constant.ViewMapping.STATIC_RESOURCES_PATH;
import static com.supervisor.util.constant.ViewMapping.VIEWS_ROOT_PATH;

@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF" + VIEWS_ROOT_PATH + "/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        addResourceHandlerToRegistry(registry, STATIC_LIBRARIES_ROOT);
        addResourceHandlerToRegistry(registry, STATIC_JS_ROOT);
        addResourceHandlerToRegistry(registry, STATIC_CSS_ROOT);
    }

    private void addResourceHandlerToRegistry(ResourceHandlerRegistry registry, String context) {
        registry.addResourceHandler(context + "/**")
                .addResourceLocations(STATIC_RESOURCES_PATH + context + "/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
