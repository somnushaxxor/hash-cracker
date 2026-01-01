package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.TaskPartProducerPort;
import ru.nsu.fit.kolesnik.hashcracker.schema.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashManagerRequest;

@RequiredArgsConstructor
@Component
public class RabbitTaskPartProducerAdapter implements TaskPartProducerPort {
    private final AmqpTemplate amqpTemplate;
    private final Queue taskPartsQueue;

    @Override
    public void produce(Task task) {
        for (int partIndex = 0; partIndex < task.getPartsNumber(); partIndex++) {
            CrackHashManagerRequest managerRequest = createManagerRequestFrom(task, partIndex);
            amqpTemplate.convertAndSend(taskPartsQueue.getName(), managerRequest);
        }
    }

    private CrackHashManagerRequest createManagerRequestFrom(Task task, int partIndex) {
        CrackHashManagerRequest managerRequest = new CrackHashManagerRequest();
        managerRequest.setTaskId(task.getId().toString());
        managerRequest.setPartIndex(partIndex);
        managerRequest.setPartCount(task.getPartsNumber());
        managerRequest.setHash(task.getHash());
        managerRequest.setMaxLength(task.getMaxLength());
        Alphabet managerRequestAlphabet = new Alphabet();
        managerRequestAlphabet.setSymbols(task.getAlphabet().toStringList());
        managerRequest.setAlphabet(managerRequestAlphabet);
        return managerRequest;
    }
}
