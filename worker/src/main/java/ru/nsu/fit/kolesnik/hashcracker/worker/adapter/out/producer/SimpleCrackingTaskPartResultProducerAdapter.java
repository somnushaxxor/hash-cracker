package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import org.example.WorkerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.exception.CrackingTaskResultSendingException;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer.CrackingTaskPartResultProducerPort;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SimpleCrackingTaskPartResultProducerAdapter implements CrackingTaskPartResultProducerPort {
    private final RestClient restClient; // TODO

    @Value("${manager.task-result-sending.uri}")
    private String managerTaskResultSendingUri; // TODO

    @Override
    public void produce(UUID taskId, int partIndex, List<String> resultWords) {
        restClient.patch()
                .uri(managerTaskResultSendingUri)
                .bodyValue(new WorkerResponse(taskId, partIndex, resultWords))
                .retrieve()
                .toBodilessEntity()
                .toFuture()
                .exceptionally(throwable -> {
                    throw new CrackingTaskResultSendingException(throwable);
                });
    }
}
