package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
public class DbAlphabet {
    @Field("characters")
    private List<Character> characters;
}
