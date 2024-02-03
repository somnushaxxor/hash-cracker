package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.TaskPersistencePort;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryTaskPersistenceAdapter implements TaskPersistencePort {
    private final ConcurrentMap<UUID, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public void save(Task request) {
        tasks.put(request.getId(), request);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        Task request = tasks.get(id);
        if (request == null) {
            return Optional.empty();
        }
        return Optional.of(request);
    }
}
