package ru.nsu.fit.kolesnik.hashcracker.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nsu.fit.kolesnik.hashcracker.manager.exception.CrackingTaskSendingException;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class CrackingTaskSendingServiceImpl implements CrackingTaskSendingService {

    private final WebClient webClient;
    private final List<String> alphabet;
    @Value("${worker.task-sending.uri}")
    private String workerTaskSendingUri;

    @Override
    public void sendTasksToWorkers(CrackingRequest crackingRequest) {
        final List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        for (int partIndex = 0; partIndex < crackingRequest.getTasksNumber(); partIndex++) {
            final CrackingTaskManagerRequest managerRequest = createManagerRequestFrom(crackingRequest, partIndex);

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

    private CrackingTaskManagerRequest createManagerRequestFrom(CrackingRequest crackingRequest, int partIndex) {
        final CrackingTaskManagerRequest managerRequest = new CrackingTaskManagerRequest();
        managerRequest.setRequestId(crackingRequest.getId().toString());
        managerRequest.setPartIndex(partIndex);
        managerRequest.setPartsNumber(crackingRequest.getTasksNumber());
        managerRequest.setHash(crackingRequest.getHash());
        managerRequest.setMaxLength(crackingRequest.getMaxLength());
        final CrackingTaskManagerRequest.Alphabet managerRequestAlphabet = new CrackingTaskManagerRequest.Alphabet();
        managerRequestAlphabet.getSymbols().addAll(alphabet);
        managerRequest.setAlphabet(managerRequestAlphabet);
        return managerRequest;
    }

}
