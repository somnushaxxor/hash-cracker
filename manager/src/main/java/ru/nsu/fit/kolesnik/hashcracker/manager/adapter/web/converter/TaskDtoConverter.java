package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.converter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskCreationResponseDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskStatusDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskStatusResponseDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskStatus;

@Component
public class TaskDtoConverter {
    public TaskCreationResponseDto convertToCreationResponse(Task task) {
        return new TaskCreationResponseDto(task.getId());
    }

    public TaskStatusResponseDto convertToStatusResponse(Task task) {
        return new TaskStatusResponseDto(convertToStatusDto(task.getStatus()), task.getResultWords());
    }

    private TaskStatusDto convertToStatusDto(TaskStatus status) {
        return switch (status) {
            case CREATED, IN_PROGRESS -> TaskStatusDto.IN_PROGRESS;
            case READY -> TaskStatusDto.READY;
            case ERROR -> TaskStatusDto.ERROR;
        };
    }
}
