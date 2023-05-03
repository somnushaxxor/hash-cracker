package ru.nsu.fit.kolesnik.hashcracker.manager.service;

import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

public interface CrackingRequestService {

    CrackingRequest createCrackingRequest(CrackingRequestCreationRequest creationRequest);

    CrackingRequest getCrackingRequestById(String id);

    void updateCrackingRequestResultsBy(CrackingTaskWorkerResponse workerResponse);

}
