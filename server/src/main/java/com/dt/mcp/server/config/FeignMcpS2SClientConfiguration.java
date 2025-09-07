package com.dt.mcp.server.config;


import com.dt.mcp.server.interceptor.FeignInternalMcpRequestInterceptor;
import com.dt.platform.feignclient.config.MetricsAwareFeignConfiguration;
import com.dt.platform.feignclient.interceptor.FeignInternalMicroServiceRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(MetricsAwareFeignConfiguration.class)
public class FeignMcpS2SClientConfiguration {

    @Bean
    public FeignInternalMcpRequestInterceptor feignInternalMcpRequestInterceptor() {
        return new FeignInternalMcpRequestInterceptor();
    }
}
