package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document("tasks")
public class DbTask {
    @Id
    private UUID id;

    @Field("status")
    private DbTaskStatus status;

    @Field("hash")
    private String hash;

    @Field("max_length")
    private Integer maxLength;

    @Field("alphabet")
    private DbAlphabet alphabet;

    @Field("parts_number")
    private Integer partsNumber;

    @Field("completed_parts_indexes")
    private Set<Integer> completedPartsIndexes;

    @Field("result_words")
    private List<String> resultWords;
}
