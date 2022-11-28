package com.sxs.config;

import com.sxs.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/frond/**").addResourceLocations("classpath:/frond/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .excludePathPatterns(new String[]{"/backend/page/login/login.html",
                "/error",
                "/employee/login",
                "/backend/plugins/**",
                "/backend/api/**",
                "/backend/images/**",
                "/backend/styles/**",
                "/backend/js/**",
                "/backend/favicon.ico",
                });
    }
}
