package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskCreationRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(TaskCreationRequest taskCreationRequest);

    void produceTask(UUID taskId);

    Task getTaskById(UUID id);

    void updateTaskResultsBy(UUID taskId, int partIndex, List<String> resultWords);

    void cancelTask(UUID taskId);

    List<Task> getCreatedTasks();
}
