package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.converter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskCreationRequestDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskCreationRequest;

@Component
public class TaskCreationRequestDtoConverter {
    public TaskCreationRequest convertToDomain(TaskCreationRequestDto dto) {
        return new TaskCreationRequest(dto.hash(), dto.maxLength());
    }
}
