package ru.nsu.fit.kolesnik.hashcracker.worker.core.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Alphabet {
    private final Set<Character> characters;

    public List<String> toStringList() {
        return characters.stream()
                .map(Object::toString)
                .toList();
    }
}
