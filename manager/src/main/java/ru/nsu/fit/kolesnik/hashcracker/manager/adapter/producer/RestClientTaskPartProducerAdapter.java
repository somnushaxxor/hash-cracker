package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import ru.nsu.fit.kolesnik.hashcracker.manager.configuration.WorkerConfigurationProperties;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.TaskPartProducerPort;
import ru.nsu.fit.kolesnik.hashcracker.schema.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashManagerRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestClientTaskPartProducerAdapter implements TaskPartProducerPort {
    private final RestClient restClient;
    private final WorkerConfigurationProperties workerConfigurationProperties;

    @Override
    public void produce(Task task) {
        for (int partIndex = 0; partIndex < task.getPartsNumber(); partIndex++) {
            CrackHashManagerRequest managerRequest = createManagerRequestFrom(task, partIndex);
            try {
                restClient
                        .post()
                        .uri(workerConfigurationProperties.getTaskPartSendingUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(managerRequest)
                        .retrieve()
                        .toBodilessEntity();
            } catch (ResourceAccessException e) {
                log.error("Error occurred while sending task part to worker", e);
            }
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
