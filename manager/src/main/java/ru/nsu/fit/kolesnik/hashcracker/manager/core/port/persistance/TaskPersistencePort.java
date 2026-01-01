package ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskPersistencePort {
    void save(Task task);

    Optional<Task> findById(UUID id);

    List<Task> getCreatedTasks();
}
