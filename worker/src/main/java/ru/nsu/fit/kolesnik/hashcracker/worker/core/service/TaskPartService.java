package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;

import java.util.concurrent.CompletableFuture;

public interface TaskPartService {
    void scheduleTaskPartResultResolving(TaskPart taskPart);
}
