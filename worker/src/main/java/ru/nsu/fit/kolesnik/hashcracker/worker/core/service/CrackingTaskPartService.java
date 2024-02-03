package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.CrackingTaskPart;

public interface CrackingTaskPartService {
    void scheduleTaskPartExecution(CrackingTaskPart taskPart);
}
