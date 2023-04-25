package ru.nsu.fit.kolesnik.hashcracker.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.kolesnik.hashcracker.manager.service.CrackingRequestService;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/api/manager/hash/crack/request")
public class CrackingRequestInternalController {

    private final CrackingRequestService crackingRequestService;

    @PatchMapping
    public void updateCrackingRequestResultsBy(@RequestBody CrackingTaskWorkerResponse workerResponse) {
        crackingRequestService.updateCrackingRequestResultsBy(workerResponse);
    }

}
