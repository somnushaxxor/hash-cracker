package ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTaskPart;

public interface CrackingTaskPartProducerPort {
    void produce(CrackingTaskPart crackingTaskPart);
}
