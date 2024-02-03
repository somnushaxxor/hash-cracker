package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.exception.CrackingTaskSendingException;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTaskPart;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.CrackingTaskPartProducerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class SimpleCrackingTaskPartProducerAdapter implements CrackingTaskPartProducerPort {
    private final RestClient restClient;

    @Value("${worker.task-sending.uri}")
    private String workerTaskSendingUri;

    @Override
    public void produce(CrackingTaskPart crackingTaskPart) {
        final List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        for (int partIndex = 0; partIndex < crackingTask.getTasksNumber(); partIndex++) {
            final CrackingTaskManagerRequest managerRequest = createManagerRequestFrom(crackingTask, partIndex);

            final CompletableFuture<?> completableFuture = webClient.post()
                    .uri(workerTaskSendingUri)
                    .bodyValue(managerRequest)
                    .retrieve()
                    .toBodilessEntity()
                    .toFuture();
            completableFutures.add(completableFuture);
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]))
                .exceptionally(throwable -> {
                    throw new CrackingTaskSendingException(throwable);
                })
                .join();
    }

    private CrackingTaskManagerRequest createManagerRequestFrom(CrackingTask crackingTask, int partIndex) {
        final CrackingTaskManagerRequest managerRequest = new CrackingTaskManagerRequest();
        managerRequest.setRequestId(crackingTask.getId().toString());
        managerRequest.setPartIndex(partIndex);
        managerRequest.setPartsNumber(crackingTask.getTasksNumber());
        managerRequest.setHash(crackingTask.getHash());
        managerRequest.setMaxLength(crackingTask.getMaxLength());
        final CrackingTaskManagerRequest.Alphabet managerRequestAlphabet = new CrackingTaskManagerRequest.Alphabet();
        managerRequestAlphabet.getSymbols().addAll(alphabet);
        managerRequest.setAlphabet(managerRequestAlphabet);
        return managerRequest;
    }
}
