package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;
import ru.nsu.fit.kolesnik.hashcracker.worker.exception.CrackingTaskResultSendingException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CrackingTaskResultSendingServiceImpl implements CrackingTaskResultSendingService {

    private final WebClient webClient;
    @Value("${manager.task-result-sending.uri}")
    private String managerTaskResultSendingUri;

    @Override
    public void sendTaskResultToManager(List<String> suitableWords, CrackingTaskManagerRequest managerRequest) {
        final CrackingTaskWorkerResponse workerResponse = createWorkerResponseFrom(suitableWords, managerRequest);

        webClient.patch()
                .uri(managerTaskResultSendingUri)
                .bodyValue(workerResponse)
                .retrieve()
                .toBodilessEntity()
                .toFuture()
                .exceptionally(throwable -> {
                    throw new CrackingTaskResultSendingException(throwable);
                });
    }

    private CrackingTaskWorkerResponse createWorkerResponseFrom(List<String> suitableWords,
                                                                CrackingTaskManagerRequest managerRequest) {
        final CrackingTaskWorkerResponse workerResponse = new CrackingTaskWorkerResponse();
        workerResponse.setRequestId(managerRequest.getRequestId());
        workerResponse.setPartIndex(managerRequest.getPartIndex());
        CrackingTaskWorkerResponse.Answers answers = new CrackingTaskWorkerResponse.Answers();
        answers.getWords().addAll(suitableWords);
        workerResponse.setAnswers(answers);
        return workerResponse;
    }

}
