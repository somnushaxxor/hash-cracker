package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashWorkerResponse;
import ru.nsu.fit.kolesnik.hashcracker.schema.Result;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer.TaskPartResultProducerPort;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RabbitTaskPartResultProducerAdapter implements TaskPartResultProducerPort {
    private final AmqpTemplate amqpTemplate;
    private final Queue taskPartsResultsQueue;

    @Override
    public void produce(UUID taskId, int partIndex, List<String> resultWords) {
        CrackHashWorkerResponse workerResponse = crackWorkerResponseFrom(taskId, partIndex, resultWords);
        amqpTemplate.convertAndSend(taskPartsResultsQueue.getName(), workerResponse);
    }

    private CrackHashWorkerResponse crackWorkerResponseFrom(UUID taskId, int partIndex, List<String> resultWords) {
        CrackHashWorkerResponse workerResponse = new CrackHashWorkerResponse();
        workerResponse.setTaskId(taskId.toString());
        workerResponse.setPartIndex(partIndex);
        Result result = new Result();
        result.setWords(resultWords);
        workerResponse.setResult(result);
        return workerResponse;
    }
}
