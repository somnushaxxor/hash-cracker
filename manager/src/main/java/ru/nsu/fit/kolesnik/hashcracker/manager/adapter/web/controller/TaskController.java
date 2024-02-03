package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.converter.TaskCreationRequestDtoConverter;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.converter.TaskDtoConverter;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskCreationRequestDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskCreationResponseDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto.TaskStatusResponseDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.exception.NotFoundException;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.TaskService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hash")
public class TaskController {
    private final TaskService taskService;
    private final TaskCreationRequestDtoConverter creationRequestDtoConverter;
    private final TaskDtoConverter taskDtoConverter;

    @PostMapping("/crack")
    public ResponseEntity<TaskCreationResponseDto> createTask(@Valid @RequestBody TaskCreationRequestDto creationRequestDto) {
        TaskCreationRequest creationRequest = creationRequestDtoConverter.convertToDomain(creationRequestDto);
        Task task = taskService.createTask(creationRequest);
        return ResponseEntity.ok(taskDtoConverter.convertToCreationResponse(task));
    }

    @GetMapping("/status")
    public ResponseEntity<TaskStatusResponseDto> getStatus(@RequestParam UUID taskId) {
        Task task;
        try {
            task = taskService.getTaskById(taskId);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(taskDtoConverter.convertToStatusResponse(task));
    }
}
