package ru.nsu.fit.kolesnik.hashcracker.manager.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class CrackingTask {
    private final UUID id;
    private Status status;
    private final String hash;
    private final int maxLength;
    private final Set<Character> alphabet;
    private final int tasksNumber; // FIXME
    private final List<Integer> completedTasks; // FIXME
    private final List<String> data;

    public CrackingTask(String hash, int maxLength, Set<Character> alphabet, int tasksNumber) {
        this.id = UUID.randomUUID();
        this.status = Status.IN_PROGRESS;
        this.hash = hash;
        this.maxLength = maxLength;
        this.alphabet = alphabet;
        this.tasksNumber = tasksNumber;
        this.completedTasks = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    public enum Status {
        IN_PROGRESS,
        READY,
        ERROR
    }
}
