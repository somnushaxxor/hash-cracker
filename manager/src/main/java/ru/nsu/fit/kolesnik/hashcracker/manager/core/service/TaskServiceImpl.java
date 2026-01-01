package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.exception.NotFoundException;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskStatus;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.TaskPersistencePort;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.TaskPartProducerPort;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskPartProducerPort taskPartProducerPort;
    private final TaskPersistencePort taskPersistencePort;
    private final TaskPartsNumberResolver taskPartsNumberResolver;
    private final Alphabet defaultAlphabet;

    @Override
    public Task createTask(TaskCreationRequest creationRequest) {
        int taskPartsNumber = taskPartsNumberResolver.resolveTaskPartsNumber();
        Task task = new Task(
                creationRequest.getHash(),
                creationRequest.getMaxLength(),
                defaultAlphabet,
                taskPartsNumber
        );
        taskPersistencePort.save(task);
        return task;
    }

    @Override
    public void produceTask(UUID taskId) {
        Task task = getTaskById(taskId);
        taskPartProducerPort.produce(task);
        task.setStatus(TaskStatus.IN_PROGRESS); // todo
        taskPersistencePort.save(task);
    }

    @Override
    public void cancelTask(UUID taskId) {
        Task task = getTaskById(taskId);
        if (task.getStatus() == TaskStatus.READY) {
            return;
        }
        task.setStatus(TaskStatus.ERROR);
        taskPersistencePort.save(task);
    }

    @Override
    public List<Task> getCreatedTasks() {
        return taskPersistencePort.getCreatedTasks();
    }

    @Override
    public Task getTaskById(UUID id) {
        return taskPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id.toString()));
    }

    @Override
    public void updateTaskResultsBy(UUID taskId, int partIndex, List<String> resultWords) {
        Task task = getTaskById(taskId);
        if (task.getStatus() == TaskStatus.READY || task.getStatus() == TaskStatus.ERROR) {
            return;
        }
        if (task.getCompletedPartsIndexes().contains(partIndex)) {
            return;
        }
        task.getResultWords().addAll(resultWords);
        task.getCompletedPartsIndexes().add(partIndex);
        if (task.getPartsNumber() == task.getCompletedPartsIndexes().size()) {
            task.setStatus(TaskStatus.READY);
        }
        taskPersistencePort.save(task);
    }
}
