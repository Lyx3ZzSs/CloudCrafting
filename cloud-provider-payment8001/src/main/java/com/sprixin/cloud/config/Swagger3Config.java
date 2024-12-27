package com.sprixin.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi payApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder().group("其他微服务模块").pathsToMatch("/other/**", "/others").build();
    }

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder().group("客户微服务模块").pathsToMatch("/customer/**", "/customers").build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI().info(new Info()
                .title("CloudCrafting")
                .description("通用设计rest")
                .version("v1.0"))
                .externalDocs(new ExternalDocumentation().description("www.sprixin.com").url("https://yiyan.baidu.com"));
    }
}
