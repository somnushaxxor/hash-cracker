package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbTaskStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface DbTaskRepository extends MongoRepository<DbTask, UUID> {
    List<DbTask> findAllByStatus(DbTaskStatus status);
}
