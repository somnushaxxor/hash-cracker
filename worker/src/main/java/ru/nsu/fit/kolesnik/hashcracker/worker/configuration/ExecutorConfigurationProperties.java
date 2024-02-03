package ru.nsu.fit.kolesnik.hashcracker.worker.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "executor")
@Configuration
public class ExecutorConfigurationProperties {
    private int maxPoolSize;
}
