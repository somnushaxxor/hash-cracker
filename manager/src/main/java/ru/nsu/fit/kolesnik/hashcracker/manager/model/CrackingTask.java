package ru.nsu.fit.kolesnik.hashcracker.manager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;

@Getter
@Setter
@Document("tasks")
public class CrackingTask {

    @Id
    private String id;

    @Field("manager_request")
    private CrackingTaskManagerRequest managerRequest;

    public CrackingTask(CrackingTaskManagerRequest managerRequest) {
        this.managerRequest = managerRequest;
    }

}
