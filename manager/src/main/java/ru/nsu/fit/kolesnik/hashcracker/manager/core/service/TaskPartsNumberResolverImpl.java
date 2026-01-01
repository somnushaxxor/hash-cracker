package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.configuration.WorkersConfigurationProperties;

@RequiredArgsConstructor
@Service
public class TaskPartsNumberResolverImpl implements TaskPartsNumberResolver {
    private final WorkersConfigurationProperties workersConfigurationProperties;

    @Override
    public int resolveTaskPartsNumber() {
        return workersConfigurationProperties.getNumber();
    }
}
