package ru.nsu.fit.kolesnik.hashcracker.worker.adapter.in.web.converter;

import org.example.ManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.CrackingTaskPart;

public final class CrackingTaskPartConverter {
    private CrackingTaskPartConverter() {
    }

    public static CrackingTaskPart convertToDomain(ManagerRequest managerRequest) {
        return new CrackingTaskPart(
                managerRequest.taskId(),
                managerRequest.partIndex(),
                managerRequest.totalPartsNumber(),
                managerRequest.hash(),
                managerRequest.maxLength(),
                managerRequest.alphabet()
        );
    }
}
