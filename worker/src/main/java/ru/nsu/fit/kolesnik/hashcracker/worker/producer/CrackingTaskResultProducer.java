package ru.nsu.fit.kolesnik.hashcracker.worker.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

@RequiredArgsConstructor
@Component
public class CrackingTaskResultProducer {

    private final AmqpTemplate amqpTemplate;
    private final Queue taskResultsQueue;

    public void produce(CrackingTaskWorkerResponse workerResponse) {
        amqpTemplate.convertAndSend(taskResultsQueue.getName(), workerResponse);
    }

}
