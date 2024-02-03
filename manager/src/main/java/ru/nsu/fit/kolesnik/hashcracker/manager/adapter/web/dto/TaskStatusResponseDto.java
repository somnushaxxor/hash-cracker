package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto;

import java.util.List;

public record TaskStatusResponseDto(TaskStatusDto status, List<String> data) {
}
