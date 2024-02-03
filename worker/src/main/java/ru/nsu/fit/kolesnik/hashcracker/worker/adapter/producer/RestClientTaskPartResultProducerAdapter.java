package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashWorkerResponse;
import ru.nsu.fit.kolesnik.hashcracker.schema.Result;
import ru.nsu.fit.kolesnik.hashcracker.worker.configuration.ManagerConfigurationProperties;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer.TaskPartResultProducerPort;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestClientTaskPartResultProducerAdapter implements TaskPartResultProducerPort {
    private final RestClient restClient;
    private final ManagerConfigurationProperties managerConfigurationProperties;

    @Override
    public void produce(UUID taskId, int partIndex, List<String> resultWords) {
        try {
            restClient
                    .patch()
                    .uri(managerConfigurationProperties.getTaskPartResultSendingUri())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(crackWorkerResponseFrom(taskId, partIndex, resultWords))
                    .retrieve()
                    .toBodilessEntity();
        } catch (
                ResourceAccessException e) {
            log.error("Error occurred while sending task part result to manager", e);
        }
    }

    private CrackHashWorkerResponse crackWorkerResponseFrom(UUID taskId, int partIndex, List<String> resultWords) {
        CrackHashWorkerResponse workerResponse = new CrackHashWorkerResponse();
        workerResponse.setTaskId(taskId.toString());
        workerResponse.setPartIndex(partIndex);
        Result result = new Result();
        result.setWords(resultWords);
        workerResponse.setResult(result);
        return workerResponse;
    }
}
