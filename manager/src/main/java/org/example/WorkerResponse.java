package org.example;

import java.util.Set;
import java.util.UUID;

public record WorkerResponse(UUID taskId, int partIndex, Set<String> resultWords) {
}
