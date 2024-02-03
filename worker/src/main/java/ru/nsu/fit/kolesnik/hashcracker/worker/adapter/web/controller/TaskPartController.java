package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.schema.CrackHashManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.adapter.web.converter.TaskPartConverter;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.service.TaskPartService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/worker/hash/crack/task/part")
public class TaskPartController {
    private final TaskPartConverter taskPartConverter;
    private final TaskPartService taskPartService;

    @PostMapping
    public ResponseEntity<Void> scheduleTaskPartExecution(@RequestBody CrackHashManagerRequest managerRequest) {
        taskPartService.scheduleTaskPartResultResolving(taskPartConverter.convertToDomain(managerRequest));
        return ResponseEntity.ok().build();
    }
}
