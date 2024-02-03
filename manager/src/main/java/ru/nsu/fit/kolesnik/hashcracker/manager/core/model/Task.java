package ru.nsu.fit.kolesnik.hashcracker.manager.core.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Task {
    private final UUID id;
    private TaskStatus status;
    private final String hash;
    private final int maxLength;
    private final Alphabet alphabet;
    private final int partsNumber;
    private final List<Integer> completedPartsIndexes;
    private final List<String> data;

    public Task(String hash, int maxLength, Alphabet alphabet, int partsNumber) {
        this.id = UUID.randomUUID();
        this.status = TaskStatus.IN_PROGRESS;
        this.hash = hash;
        this.maxLength = maxLength;
        this.alphabet = alphabet;
        this.partsNumber = partsNumber;
        this.completedPartsIndexes = new ArrayList<>();
        this.data = new ArrayList<>();
    }
}
