package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
public class CrackingTaskServiceImpl implements CrackingTaskService {

    private final Executor crackingTaskExecutor;
    private final CrackingTaskResultSendingService crackingTaskResultSendingService;

    @Override
    public void executeCrackingTask(CrackingTaskManagerRequest managerRequest) {
        CompletableFuture.supplyAsync(() ->
                                CrackingTaskOperations.executeTask(managerRequest)
                        , crackingTaskExecutor)
                .thenAccept(suitableWords ->
                        crackingTaskResultSendingService.sendTaskResult(suitableWords, managerRequest)
                );
    }

}
