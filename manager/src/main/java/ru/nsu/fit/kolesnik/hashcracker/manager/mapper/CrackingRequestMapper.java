package ru.nsu.fit.kolesnik.hashcracker.manager.mapper;

import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestCreationResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.dto.CrackingRequestStatusResponse;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;

public final class CrackingRequestMapper {

    private CrackingRequestMapper() {
    }

    public static CrackingRequestCreationResponse mapToCreationResponse(CrackingRequest crackingRequest) {
        return new CrackingRequestCreationResponse(crackingRequest.getId());
    }

    public static CrackingRequestStatusResponse mapToStatusResponse(CrackingRequest crackingRequest) {
        return new CrackingRequestStatusResponse(crackingRequest.getStatus(), crackingRequest.getData());
    }

}
