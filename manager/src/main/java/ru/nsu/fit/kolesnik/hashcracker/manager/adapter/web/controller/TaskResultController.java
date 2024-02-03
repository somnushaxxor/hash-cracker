package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.TaskService;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashWorkerResponse;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/manager/hash/crack/task")
public class TaskResultController {
    private final TaskService taskService;

    @PatchMapping
    public ResponseEntity<Void> updateCrackingTaskResultsBy(@RequestBody CrackHashWorkerResponse workerResponse) {
        taskService.updateTaskResultsBy(
                UUID.fromString(workerResponse.getTaskId()),
                workerResponse.getPartIndex(),
                workerResponse.getResult().getWords()
        );
        return ResponseEntity.ok().build();
    }
}
