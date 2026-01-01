package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "workers")
@Configuration
public class WorkersConfigurationProperties {
    private int number;
}
