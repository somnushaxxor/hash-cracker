package ru.nsu.fit.kolesnik.hashcracker.worker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
class CrackingTaskExecutorConfiguration {
    @Value("${cracking-task.executor.max-pool-size}")
    private Integer crackingTaskExecutorMaxPoolSize;

    @Bean
    Executor crackingTaskExecutor() {
        final ThreadPoolTaskExecutor crackingTaskExecutor = new ThreadPoolTaskExecutor();
        crackingTaskExecutor.setMaxPoolSize(crackingTaskExecutorMaxPoolSize);
        return crackingTaskExecutor;
    }
}
