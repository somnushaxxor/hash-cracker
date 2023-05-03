package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;
import ru.nsu.fit.kolesnik.hashcracker.worker.producer.CrackingTaskResultProducer;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RabbitCrackingTaskResultSendingServiceImpl implements CrackingTaskResultSendingService {

    private final CrackingTaskResultProducer producer;

    @Override
    public void sendTaskResult(List<String> suitableWords, CrackingTaskManagerRequest managerRequest) {
        final CrackingTaskWorkerResponse workerResponse = createWorkerResponseFrom(suitableWords, managerRequest);
        producer.produce(workerResponse);
    }

    private CrackingTaskWorkerResponse createWorkerResponseFrom(List<String> suitableWords,
                                                                CrackingTaskManagerRequest managerRequest) {
        final CrackingTaskWorkerResponse workerResponse = new CrackingTaskWorkerResponse();
        workerResponse.setRequestId(managerRequest.getRequestId());
        workerResponse.setPartIndex(managerRequest.getPartIndex());
        CrackingTaskWorkerResponse.Answers answers = new CrackingTaskWorkerResponse.Answers();
        answers.getWords().addAll(suitableWords);
        workerResponse.setAnswers(answers);
        return workerResponse;
    }

}
