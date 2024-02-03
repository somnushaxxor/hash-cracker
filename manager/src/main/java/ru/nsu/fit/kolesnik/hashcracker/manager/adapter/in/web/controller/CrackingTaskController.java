package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.converter.CrackingTaskDtoConverter;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskCreationResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskStatusResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.CrackingTaskService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hash")
public class CrackingTaskController {
    private final CrackingTaskService crackingTaskService;

    @PostMapping("/crack")
    public CrackingTaskCreationResponse createTask(@Valid @RequestBody CrackingTaskCreationRequest creationRequest) {
        final CrackingTask crackingTask = crackingTaskService.createCrackingTask(
                creationRequest.hash(), creationRequest.maxLength()
        );
        return CrackingTaskDtoConverter.convertToCreationResponse(crackingTask);
    }

    @GetMapping("/status")
    public CrackingTaskStatusResponse getStatus(@RequestParam UUID taskId) {
        final CrackingTask crackingTask = crackingTaskService.getCrackingTaskById(taskId);
        return CrackingTaskDtoConverter.convertToStatusResponse(crackingTask);
    }
}
