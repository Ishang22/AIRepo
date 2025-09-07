package com.dt.mcp.server.config;

import com.dt.mcp.server.service.AttributeService;
import com.dt.mcp.server.service.CartService;
import com.dt.mcp.server.service.CatalogService;
import com.dt.mcp.server.service.UserService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ToolsConfig {

    @Bean
    public ToolCallbackProvider tools(UserService userService, CatalogService catalogService, CartService cartService, AttributeService attributeService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(userService, catalogService, cartService,attributeService)
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sse").allowedOrigins("*");
            }
        };
    }
}
