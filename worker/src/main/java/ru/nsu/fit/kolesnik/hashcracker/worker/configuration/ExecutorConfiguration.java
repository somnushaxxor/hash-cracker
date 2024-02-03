package ru.nsu.fit.kolesnik.hashcracker.worker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
class ExecutorConfiguration {
    @Bean
    Executor taskExecutor(ExecutorConfigurationProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        return executor;
    }
}
