package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    String uploadPath="file:///D:\\web_0226_kms\\_myProject\\_java\\_fileUpload\\";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/upload/**")
               .addResourceLocations(uploadPath);
       // registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
    }
}
