package com.dogsole.developersite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 이미지를 출력할 때는 spring 에게 해당 경로를 허락해줘야 함
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    String PermittedPath = "file:///" +System.getProperty("user.dir") + "/src/main/resources/static/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // PermittedPath 경로들/+a
                .addResourceLocations(PermittedPath); // 로컬에 저장된 파일을 읽어 올 root 경로 설정
    }
}