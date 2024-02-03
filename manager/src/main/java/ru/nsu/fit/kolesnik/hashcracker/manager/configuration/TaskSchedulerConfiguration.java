package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
class TaskSchedulerConfiguration {
    @Bean
    TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
