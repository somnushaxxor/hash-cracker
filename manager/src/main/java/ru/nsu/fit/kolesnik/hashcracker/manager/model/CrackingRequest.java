package ru.nsu.fit.kolesnik.hashcracker.manager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document("requests")
public class CrackingRequest {

    @Id
    private String id;

    @Field("status")
    private CrackingRequestStatus status;

    @Field("hash")
    private String hash;

    @Field("max_length")
    private Integer maxLength;

    @Field("tasks_number")
    private Integer tasksNumber;

    @Field("completed_tasks")
    private Set<Integer> completedTasks;

    @Field("data")
    private List<String> data;

    public CrackingRequest(String hash, int maxLength, int tasksNumber) {
        this.status = CrackingRequestStatus.IN_PROGRESS;
        this.hash = hash;
        this.maxLength = maxLength;
        this.tasksNumber = tasksNumber;
        this.completedTasks = new HashSet<>();
        this.data = new ArrayList<>();
    }

}
