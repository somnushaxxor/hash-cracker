package ru.nsu.fit.kolesnik.hashcracker.worker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.service.CrackingTaskService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/worker/hash/crack/task")
public class CrackingTaskController {

    private final CrackingTaskService crackingTaskService;

    @PostMapping
    public void executeCrackingTask(@RequestBody CrackingTaskManagerRequest managerRequest) {
        crackingTaskService.executeCrackingTask(managerRequest);
    }

}
