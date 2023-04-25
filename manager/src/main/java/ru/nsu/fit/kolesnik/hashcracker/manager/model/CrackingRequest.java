package ru.nsu.fit.kolesnik.hashcracker.manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CrackingRequest {

    private final UUID id;
    private CrackingRequestStatus status;
    private final String hash;
    private final int maxLength;
    private final int tasksNumber;
    private List<Integer> completedTasks;
    private final List<String> data;

    public CrackingRequest(String hash, int maxLength, int tasksNumber) {
        this.id = UUID.randomUUID();
        this.status = CrackingRequestStatus.IN_PROGRESS;
        this.hash = hash;
        this.maxLength = maxLength;
        this.tasksNumber = tasksNumber;
        this.completedTasks = new ArrayList<>();
        this.data = new ArrayList<>();
    }

}
