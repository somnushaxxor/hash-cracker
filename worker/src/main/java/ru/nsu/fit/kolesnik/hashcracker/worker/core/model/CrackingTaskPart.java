package ru.nsu.fit.kolesnik.hashcracker.worker.core.model;

import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class CrackingTaskPart {
    private final UUID taskId;
    private final int index;
    private final int totalPartsNumber;
    private final String hash;
    private final int maxLength;
    private final Set<Character> alphabet;
}
