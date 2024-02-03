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
        return new TaskStatusResponseDto(convertToStatusDto(task.getStatus()), task.getData());
    }

    private TaskStatusDto convertToStatusDto(TaskStatus status) {
        switch (status) {
            case IN_PROGRESS -> {
                return TaskStatusDto.IN_PROGRESS;
            }
            case READY -> {
                return TaskStatusDto.READY;
            }
            case ERROR -> {
                return TaskStatusDto.ERROR;
            }
            default -> throw new IllegalArgumentException("Failed to convert given status");
        }
    }
}
