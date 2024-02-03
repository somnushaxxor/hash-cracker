package ru.nsu.fit.kolesnik.hashcracker.worker.core.model;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskPart {
    private final UUID taskId;
    private final String hash;
    private final int maxLength;
    private final int index;
    private final int partsNumber;
    private final Alphabet alphabet;
}
