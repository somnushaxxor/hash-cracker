package ru.nsu.fit.kolesnik.hashcracker.manager.repository;

import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingRequest;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryCrackingRequestRepository implements CrackingRequestRepository {

    private final ConcurrentMap<UUID, CrackingRequest> requests; // TODO проблема с get

    public InMemoryCrackingRequestRepository() {
        this.requests = new ConcurrentHashMap<>();
    }

    @Override
    public void save(CrackingRequest request) {
        requests.put(request.getId(), request);
    }

    @Override
    public Optional<CrackingRequest> findById(UUID id) {
        final CrackingRequest request = requests.get(id);
        if (request == null) {
            return Optional.empty();
        }
        return Optional.of(request);
    }

}
