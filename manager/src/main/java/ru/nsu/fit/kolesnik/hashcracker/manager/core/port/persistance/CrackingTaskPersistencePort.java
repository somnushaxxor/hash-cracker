package ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;

import java.util.Optional;
import java.util.UUID;

public interface CrackingTaskPersistencePort {
    void save(CrackingTask task);

    Optional<CrackingTask> findById(UUID id);
}
