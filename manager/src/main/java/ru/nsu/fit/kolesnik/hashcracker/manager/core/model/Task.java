package ru.nsu.fit.kolesnik.hashcracker.manager.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Task {
    private final UUID id;
    private TaskStatus status;
    private final String hash;
    private final int maxLength;
    private final Alphabet alphabet;
    private final int partsNumber;
    private final Set<Integer> completedPartsIndexes;
    private final List<String> resultWords;

    public Task(String hash, int maxLength, Alphabet alphabet, int partsNumber) {
        this.id = UUID.randomUUID();
        this.status = TaskStatus.CREATED;
        this.hash = hash;
        this.maxLength = maxLength;
        this.alphabet = alphabet;
        this.partsNumber = partsNumber;
        this.completedPartsIndexes = new HashSet<>(partsNumber);
        this.resultWords = new ArrayList<>();
    }
}
