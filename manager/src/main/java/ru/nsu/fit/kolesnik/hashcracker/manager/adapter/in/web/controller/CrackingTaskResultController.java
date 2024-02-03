package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.WorkerResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.CrackingTaskService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/manager/hash/crack/task")
public class CrackingTaskResultController {
    private final CrackingTaskService crackingTaskService;

    @PatchMapping
    public void updateCrackingTaskResultsBy(@RequestBody WorkerResponse workerResponse) {
        crackingTaskService.updateCrackingTaskResultsBy(
                workerResponse.taskId(), workerResponse.partIndex(), workerResponse.resultWords()
        );
    }
}
