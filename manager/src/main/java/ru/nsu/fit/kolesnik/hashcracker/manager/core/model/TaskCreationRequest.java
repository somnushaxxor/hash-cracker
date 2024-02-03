package ru.nsu.fit.kolesnik.hashcracker.manager.core.model;

import lombok.Data;

@Data
public class TaskCreationRequest {
    private final String hash;
    private final int maxLength;
}
