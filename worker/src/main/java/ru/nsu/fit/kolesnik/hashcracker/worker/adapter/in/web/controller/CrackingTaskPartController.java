package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.ManagerRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.worker.adapter.in.web.converter.CrackingTaskPartConverter;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.service.CrackingTaskPartService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/worker/hash/crack/task/part")
public class CrackingTaskPartController {
    private final CrackingTaskPartService crackingTaskPartService;

    @PostMapping
    public void scheduleTaskPartExecution(@RequestBody ManagerRequest managerRequest) {
        crackingTaskPartService.scheduleTaskPartExecution(CrackingTaskPartConverter.convertToDomain(managerRequest));
    }
}
