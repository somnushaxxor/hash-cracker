package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto;

import java.util.List;

public record CrackingTaskStatusResponse(CrackingTaskStatusDto status, List<String> data) {
}
