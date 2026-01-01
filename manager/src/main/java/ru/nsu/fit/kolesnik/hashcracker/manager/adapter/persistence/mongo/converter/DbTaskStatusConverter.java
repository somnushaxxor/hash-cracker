package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.converter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbTaskStatus;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskStatus;

@Component
public class DbTaskStatusConverter {
    public TaskStatus convertToDomain(DbTaskStatus dbTaskStatus) {
        return switch (dbTaskStatus) {
            case CREATED -> TaskStatus.CREATED;
            case IN_PROGRESS -> TaskStatus.IN_PROGRESS;
            case READY -> TaskStatus.READY;
            case ERROR -> TaskStatus.ERROR;
        };
    }

    public DbTaskStatus convertToDb(TaskStatus taskStatus) {
        return switch (taskStatus) {
            case CREATED -> DbTaskStatus.CREATED;
            case IN_PROGRESS -> DbTaskStatus.IN_PROGRESS;
            case READY -> DbTaskStatus.READY;
            case ERROR -> DbTaskStatus.ERROR;
        };
    }
}
