package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;

import java.util.List;

public interface TaskPartResultResolver {
    List<String> resolveTaskPartResult(TaskPart taskPart);
}
