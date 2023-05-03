package ru.nsu.fit.kolesnik.hashcracker.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.producer.CrackingTaskProducer;
import ru.nsu.fit.kolesnik.hashcracker.manager.repository.CrackingTaskRepository;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RabbitCrackingTaskSendingServiceImpl implements CrackingTaskSendingService {

    private final List<String> alphabet;
    private final CrackingTaskProducer producer;
    private final CrackingTaskRepository crackingTaskRepository;

    @Override
    public void sendTasks(CrackingRequest crackingRequest) {
        for (int partIndex = 0; partIndex < crackingRequest.getTasksNumber(); partIndex++) {
            final CrackingTaskManagerRequest managerRequest = createManagerRequestFrom(crackingRequest, partIndex);
            try {
                producer.produce(managerRequest);
            } catch (AmqpException e) {
                CrackingTask crackingTask = new CrackingTask(managerRequest);
                crackingTaskRepository.save(crackingTask);
            }
        }
    }

    private CrackingTaskManagerRequest createManagerRequestFrom(CrackingRequest crackingRequest, int partIndex) {
        final CrackingTaskManagerRequest managerRequest = new CrackingTaskManagerRequest();
        managerRequest.setRequestId(crackingRequest.getId());
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
