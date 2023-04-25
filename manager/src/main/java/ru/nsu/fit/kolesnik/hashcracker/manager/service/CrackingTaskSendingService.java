package ru.nsu.fit.kolesnik.hashcracker.manager.service;

import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;

public interface CrackingTaskSendingService {

    void sendTasksToWorkers(CrackingRequest crackingRequest);

}
