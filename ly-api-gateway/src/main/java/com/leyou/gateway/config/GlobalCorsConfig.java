package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 2.允许的域，不要写 * ，否值cookie无法使用
        config.addAllowedOrigin("http://manage.leyou.com");
        config.addAllowedOrigin("http://localhost:9528");
        // 3.是否发送cookie信息
        config.setAllowCredentials(true);
        // 4 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 5.允许的头信息
        config.addAllowedHeader("*");
        // 6.添加映射路径，当前拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);

        // 7.返回新的CorsFilter
        return new CorsFilter(configurationSource);
    }
}
