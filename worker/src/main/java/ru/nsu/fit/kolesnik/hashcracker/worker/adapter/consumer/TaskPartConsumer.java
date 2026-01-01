package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.adapter.web.converter.TaskPartConverter;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.service.TaskPartService;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = {"${task-parts.queue.name}"})
public class TaskPartConsumer {
    private final TaskPartService taskPartService;
    private final TaskPartConverter taskPartConverter;

    @RabbitHandler
    public void consume(CrackHashManagerRequest managerRequest) {
        taskPartService.scheduleTaskPartResultResolving(taskPartConverter.convertToDomain(managerRequest)); // TODO
    }
}