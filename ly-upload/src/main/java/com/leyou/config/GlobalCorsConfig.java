package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加CORS配置
        CorsConfiguration config = new CorsConfiguration();
        // 1) 允许的域，不要写*，否值cookie无法使用
        config.addAllowedOrigin("http://manage.leyou.com");
        // 2) 是否发送Cookie信息
        config.setAllowCredentials(false);
        // 3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedHeader("*");

        // 2.添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        // 3.返回新的CorsFilter
        return new CorsFilter(configSource);
    }
}
