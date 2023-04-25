package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

import java.util.List;

public interface CrackingTaskResultSendingService {

    void sendTaskResultToManager(List<String> suitableWords, CrackingTaskManagerRequest managerRequest);

}
