package ru.nsu.fit.kolesnik.hashcracker.worker.core.port.producer;

import java.util.List;
import java.util.UUID;

public interface CrackingTaskPartResultProducerPort {
    void produce(UUID taskId, int partIndex, List<String> resultWords);
}
