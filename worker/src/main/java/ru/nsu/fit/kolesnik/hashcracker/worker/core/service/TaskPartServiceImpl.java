package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer.TaskPartResultProducerPort;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
public class TaskPartServiceImpl implements TaskPartService {
    private final TaskPartResultResolver taskPartResultResolver;
    private final Executor taskExecutor;
    private final TaskPartResultProducerPort taskPartResultProducerPort;

    @Override
    public void scheduleTaskPartResultResolving(TaskPart taskPart) {
        CompletableFuture.supplyAsync(() -> taskPartResultResolver.resolveTaskPartResult(taskPart), taskExecutor)
                .thenAccept(resultWords ->
                        taskPartResultProducerPort.produce(
                                taskPart.getTaskId(),
                                taskPart.getIndex(),
                                resultWords
                        )
                );
    }
}
