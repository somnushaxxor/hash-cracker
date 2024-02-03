package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.converter;

import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskCreationResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskStatusDto;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskStatusResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;

import static ru.nsu.fit.kolesnik.hashcracker.manager.adapter.in.web.dto.CrackingTaskStatusDto.*;

public final class CrackingTaskDtoConverter {
    private CrackingTaskDtoConverter() {
    }

    public static CrackingTaskCreationResponse convertToCreationResponse(CrackingTask crackingTask) {
        return new CrackingTaskCreationResponse(crackingTask.getId());
    }

    public static CrackingTaskStatusResponse convertToStatusResponse(CrackingTask crackingTask) {
        return new CrackingTaskStatusResponse(convertToStatusDto(crackingTask.getStatus()), crackingTask.getData());
    }

    private static CrackingTaskStatusDto convertToStatusDto(CrackingTask.Status status) {
        switch (status) {
            case IN_PROGRESS -> {
                return IN_PROGRESS;
            }
            case READY -> {
                return READY;
            }
            case ERROR -> {
                return ERROR;
            }
        }
        throw new IllegalArgumentException("Failed to convert given status");
    }
}
