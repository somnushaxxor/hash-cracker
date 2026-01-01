package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.converter.DbTaskConverter;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbTaskStatus;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.repository.DbTaskRepository;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.TaskPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MongoTaskPersistencePort implements TaskPersistencePort {
    private final DbTaskRepository dbTaskRepository;
    private final DbTaskConverter dbTaskConverter;

    @Override
    public void save(Task task) {
        dbTaskRepository.save(dbTaskConverter.convertToDb(task));
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return dbTaskRepository.findById(id).map(dbTaskConverter::convertToDomain);
    }

    @Override
    public List<Task> getCreatedTasks() {
        return dbTaskRepository.findAllByStatus(DbTaskStatus.CREATED).stream()
                .map(dbTaskConverter::convertToDomain)
                .toList();
    }
}
