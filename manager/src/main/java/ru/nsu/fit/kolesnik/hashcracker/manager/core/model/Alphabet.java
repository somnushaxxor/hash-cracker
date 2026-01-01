package ru.nsu.fit.kolesnik.hashcracker.manager.core.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Alphabet {
    private final List<Character> characters;

    public List<String> toStringList() {
        return characters.stream()
                .map(Object::toString)
                .toList();
    }
}
