package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "task")
@Configuration
public class TaskConfigurationProperties {
    private Duration maxExecutionDuration;
}
