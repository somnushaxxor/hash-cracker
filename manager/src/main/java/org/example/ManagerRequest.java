package org.example;

import java.util.Set;
import java.util.UUID;

public record ManagerRequest(UUID taskId, int partIndex, int totalPartsNumber, String hash, int maxLength,
                             Set<Character> alphabet) {
} // TODO write json schema by myself
