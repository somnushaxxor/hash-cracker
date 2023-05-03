package ru.nsu.fit.kolesnik.hashcracker.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestCreationResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestStatusResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.mapper.CrackingRequestMapper;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.service.CrackingRequestService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hash")
public class CrackingRequestController {

    private final CrackingRequestService crackingRequestService;

    @PostMapping("/crack")
    public CrackingRequestCreationResponse createCrackingRequest(@Valid @RequestBody CrackingRequestCreationRequest
                                                                         creationRequest) {
        final CrackingRequest crackingRequest = crackingRequestService.createCrackingRequest(creationRequest);
        return CrackingRequestMapper.mapToCreationResponse(crackingRequest);
    }

    @GetMapping("/status")
    public CrackingRequestStatusResponse getCrackingRequestStatus(@RequestParam String requestId) {
        final CrackingRequest crackingRequest = crackingRequestService.getCrackingRequestById(requestId);
        return CrackingRequestMapper.mapToStatusResponse(crackingRequest);
    }

}
