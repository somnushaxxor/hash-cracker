package ru.nsu.fit.kolesnik.hashcracker.manager.dto;

import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequestStatus;

import java.util.List;

public record CrackingRequestStatusResponse(CrackingRequestStatus status, List<String> data) {

}
