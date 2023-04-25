package ru.nsu.fit.kolesnik.hashcracker.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.exception.NotFoundException;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequestStatus;
import ru.nsu.fit.kolesnik.hashcracker.manager.repository.CrackingRequestRepository;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CrackingRequestServiceImpl implements CrackingRequestService {

    private final CrackingTaskSendingService crackingTaskSendingService;
    private final CrackingRequestRepository crackingRequestRepository;
    private final TaskScheduler taskScheduler;
    @Value("${workers.number}")
    private Integer workersNumber;
    @Value("${cracking-request.execution.max-duration}")
    private Duration crackingRequestMaxExecutionDuration;

    @Override
    public CrackingRequest createCrackingRequest(CrackingRequestCreationRequest creationRequest) {
        final int crackingTasksNumber = countCrackingTasksNumber();
        final CrackingRequest crackingRequest = new CrackingRequest(creationRequest.hash(), creationRequest.maxLength(),
                crackingTasksNumber);
        crackingTaskSendingService.sendTasksToWorkers(crackingRequest);
        // TODO ???
        crackingRequestRepository.save(crackingRequest);
        scheduleCrackingRequestCancellation(crackingRequest);
        return crackingRequest;
    }

    private int countCrackingTasksNumber() {
        return workersNumber; // TODO ????
    }

    private void scheduleCrackingRequestCancellation(CrackingRequest crackingRequest) {
        taskScheduler.schedule(() -> cancelCrackingRequest(crackingRequest), getCrackingRequestTimeoutInstant());
    }

    private void cancelCrackingRequest(CrackingRequest crackingRequest) {
        synchronized (crackingRequest) {
            crackingRequest.setStatus(CrackingRequestStatus.ERROR);
            crackingRequestRepository.save(crackingRequest);
        }
    }

    private Instant getCrackingRequestTimeoutInstant() {
        return Instant.now().plusMillis(crackingRequestMaxExecutionDuration.toMillis());
    }

    @Override
    public CrackingRequest getCrackingRequestById(UUID id) {
        return crackingRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Request not found: " + id.toString()));
    }

    @Override
    public void updateCrackingRequestResultsBy(CrackingTaskWorkerResponse workerResponse) {
        final CrackingRequest crackingRequest = getCrackingRequestById(UUID.fromString(workerResponse.getRequestId()));
        synchronized (crackingRequest) { // TODO ?????
            if (crackingRequest.getStatus() == CrackingRequestStatus.IN_PROGRESS) {
                crackingRequest.getData().addAll(workerResponse.getAnswers().getWords());
                crackingRequest.getCompletedTasks().add(workerResponse.getPartIndex());
                if (crackingRequest.getTasksNumber() == crackingRequest.getCompletedTasks().size()) {
                    crackingRequest.setStatus(CrackingRequestStatus.READY);
                }
                crackingRequestRepository.save(crackingRequest);
            }
        }
    }

}
