package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;

import java.util.Set;
import java.util.UUID;

public interface CrackingTaskService {
    CrackingTask createCrackingTask(String hash, int maxLength);

    CrackingTask getCrackingTaskById(UUID id);

    void updateCrackingTaskResultsBy(UUID taskId, int partIndex, Set<String> resultWords);
}
