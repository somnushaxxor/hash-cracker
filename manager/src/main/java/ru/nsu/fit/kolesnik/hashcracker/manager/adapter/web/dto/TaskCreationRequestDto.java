package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreationRequestDto(@NotBlank String hash, @NotNull @Min(1) Integer maxLength) {
}
