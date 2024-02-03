package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrackingTaskCreationRequest(@NotBlank String hash, @NotNull @Min(1) Integer maxLength) {
}
