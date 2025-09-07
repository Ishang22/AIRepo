package com.dt.mcp.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.dt.mcp.server.interceptor.SyliusMcpFeignRequestInterceptor;
import com.dt.platform.feignclient.config.MetricsAwareHalFeignConfiguration;
import feign.RequestInterceptor;

/**
 * Description:<br>
 * Date: 03/09/25-2:40 pm
 *
 * @author ishangarg
 * @since
 */
@Import(MetricsAwareHalFeignConfiguration.class)
public class SyliusMcpFeignConfiguration {
    @Bean
    public SyliusMcpFeignRequestInterceptor syliusMcpFeignRequestInterceptor() {
        return new SyliusMcpFeignRequestInterceptor();
    }
}
