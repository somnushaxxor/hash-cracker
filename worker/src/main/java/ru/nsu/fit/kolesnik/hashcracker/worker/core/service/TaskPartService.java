package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;

public interface TaskPartService {
    void scheduleTaskPartResultResolving(TaskPart taskPart);
}
