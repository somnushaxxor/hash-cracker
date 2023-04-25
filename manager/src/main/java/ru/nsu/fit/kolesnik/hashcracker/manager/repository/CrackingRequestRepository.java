package ru.nsu.fit.kolesnik.hashcracker.manager.repository;

import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;

import java.util.Optional;
import java.util.UUID;

public interface CrackingRequestRepository {

    void save(CrackingRequest request);

    Optional<CrackingRequest> findById(UUID id);

}
