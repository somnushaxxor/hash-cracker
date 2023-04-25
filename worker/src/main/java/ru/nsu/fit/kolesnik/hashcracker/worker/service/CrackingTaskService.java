package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

public interface CrackingTaskService {

    void executeCrackingTask(CrackingTaskManagerRequest managerRequest);

}
