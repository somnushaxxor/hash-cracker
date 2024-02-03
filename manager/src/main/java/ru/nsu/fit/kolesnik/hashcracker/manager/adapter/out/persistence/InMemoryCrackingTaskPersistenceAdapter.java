package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.out.persistence;

import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.CrackingTaskPersistencePort;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryCrackingTaskPersistenceAdapter implements CrackingTaskPersistencePort {
    private final ConcurrentMap<UUID, CrackingTask> requests = new ConcurrentHashMap<>(); // TODO проблема с get

    @Override
    public void save(CrackingTask request) {
        requests.put(request.getId(), request);
    }

    @Override
    public Optional<CrackingTask> findById(UUID id) {
        final CrackingTask request = requests.get(id);
        if (request == null) {
            return Optional.empty();
        }
        return Optional.of(request);
    }
}
