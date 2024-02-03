package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.web.converter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskPartConverter {
    public TaskPart convertToDomain(CrackHashManagerRequest managerRequest) {
        Alphabet alphabet = new Alphabet(
                managerRequest.getAlphabet().getSymbols().stream()
                        .map(s -> s.toCharArray()[0])
                        .collect(Collectors.toSet())
        );
        return new TaskPart(
                UUID.fromString(managerRequest.getTaskId()),
                managerRequest.getHash(),
                managerRequest.getMaxLength(),
                managerRequest.getPartIndex(),
                managerRequest.getPartCount(),
                alphabet
        );
    }
}
