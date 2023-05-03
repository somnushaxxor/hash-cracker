package ru.nsu.fit.kolesnik.hashcracker.manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingTask;

@Repository
public interface CrackingTaskRepository extends MongoRepository<CrackingTask, String> {

}
