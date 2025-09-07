package com.dt.mcp.server.config;

import com.dt.mcp.server.model.HeaderContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig {

    @Bean
    public TaskDecorator threadLocalContextPropagator() {
        return runnable -> {
            Map<String, String> capturedHeaders = HeaderContext.get();
            return () -> {
                HeaderContext.set(capturedHeaders);
                try {
                    runnable.run();
                } finally {
                    HeaderContext.clear();
                }
            };
        };
    }

    @Bean
    public Executor taskExecutor(TaskDecorator threadLocalContextPropagator) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("tool-exec-");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setTaskDecorator(threadLocalContextPropagator);
        executor.initialize();
        return executor;
    }
}
