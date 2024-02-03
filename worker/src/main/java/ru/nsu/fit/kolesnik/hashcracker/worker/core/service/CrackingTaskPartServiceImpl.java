package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.CrackingTaskPart;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer.CrackingTaskPartResultProducerPort;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
public class CrackingTaskPartServiceImpl implements CrackingTaskPartService {

    private final Executor crackingTaskExecutor;
    private final CrackingTaskPartResultProducerPort crackingTaskPartResultProducerPort;

    @Override
    public void scheduleTaskPartExecution(CrackingTaskPart taskPart) {
        CompletableFuture.supplyAsync(() ->
                                CrackingTaskOperations.executeTask(managerRequest)
                        , crackingTaskExecutor)
                .thenAccept(suitableWords ->
                        crackingTaskPartResultProducerPort.sendTaskResultToManager(suitableWords, managerRequest)
                );
    }

}
