package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbAlphabet;
import ru.nsu.fit.kolesnik.hashcracker.manager.adapter.persistence.mongo.model.DbTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;

@RequiredArgsConstructor
@Component
public class DbTaskConverter {
    private final DbTaskStatusConverter statusConverter;

    public DbTask convertToDb(Task task) {
        DbTask dbTask = new DbTask();
        dbTask.setId(task.getId());
        dbTask.setStatus(statusConverter.convertToDb(task.getStatus()));
        dbTask.setHash(task.getHash());
        dbTask.setMaxLength(task.getMaxLength());
        DbAlphabet dbAlphabet = new DbAlphabet();
        dbAlphabet.setCharacters(task.getAlphabet().getCharacters());
        dbTask.setAlphabet(dbAlphabet);
        dbTask.setPartsNumber(task.getPartsNumber());
        dbTask.setCompletedPartsIndexes(task.getCompletedPartsIndexes());
        dbTask.setResultWords(task.getResultWords());
        return dbTask;
    }

    public Task convertToDomain(DbTask dbTask) {
        Alphabet alphabet = new Alphabet(dbTask.getAlphabet().getCharacters());
        return new Task(
                dbTask.getId(),
                statusConverter.convertToDomain(dbTask.getStatus()),
                dbTask.getHash(),
                dbTask.getMaxLength(),
                alphabet,
                dbTask.getPartsNumber(),
                dbTask.getCompletedPartsIndexes(),
                dbTask.getResultWords()
        );
    }
}
