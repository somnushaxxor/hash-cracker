package ru.nsu.fit.kolesnik.hashcracker.manager.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

@RequiredArgsConstructor
@Component
public class CrackingTaskProducer {

    private final AmqpTemplate amqpTemplate;
    private final Queue tasksQueue;

    public void produce(CrackingTaskManagerRequest managerRequest) {
        amqpTemplate.convertAndSend(tasksQueue.getName(), managerRequest);
    }

}
