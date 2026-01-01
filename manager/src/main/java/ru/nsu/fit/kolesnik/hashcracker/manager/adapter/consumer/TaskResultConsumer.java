package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.TaskService;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashWorkerResponse;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = {"${task-parts-results.queue.name}"})
public class TaskResultConsumer {
    private final TaskService taskService;

    @RabbitHandler
    public void consume(CrackHashWorkerResponse workerResponse) {
        taskService.updateTaskResultsBy(
                UUID.fromString(workerResponse.getTaskId()),
                workerResponse.getPartIndex(),
                workerResponse.getResult().getWords()
        );
    }
}