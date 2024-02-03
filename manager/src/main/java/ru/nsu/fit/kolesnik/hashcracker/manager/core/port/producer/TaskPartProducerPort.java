package ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer;

import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;

public interface TaskPartProducerPort {
    void produce(Task task);
}
