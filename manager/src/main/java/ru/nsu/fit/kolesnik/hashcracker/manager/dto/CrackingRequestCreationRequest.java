package ru.nsu.fit.kolesnik.hashcracker.manager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrackingRequestCreationRequest(@NotBlank String hash, @NotNull @Min(1) Integer maxLength) {

}