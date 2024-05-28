package com.demo.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangzh
 * @Date: 2024/5/24 16:20
 **/
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        // 访问路径：http://localhost:port/swagger-ui/index.html
        return new OpenAPI().info(new Info()
                .title("SpringDoc API")
                .description("SpringDoc Simple Application")
                .version("0.0.1"));
    }
}
