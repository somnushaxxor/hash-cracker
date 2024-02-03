package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.configuration.TaskConfigurationProperties;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.exception.NotFoundException;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Alphabet;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Task;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskCreationRequest;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.TaskStatus;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.TaskPersistencePort;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.TaskPartProducerPort;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskPartProducerPort taskPartProducerPort;
    private final TaskPersistencePort taskPersistencePort;
    private final TaskPartsNumberResolver taskPartsNumberResolver;
    private final TaskConfigurationProperties taskConfigurationProperties;
    private final Alphabet defaultAlphabet;
    private final TaskScheduler scheduler;
    private final Lock mutex = new ReentrantLock();

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
        taskPartProducerPort.produce(task);
        scheduleTaskCancellation(task.getId());
        return task;
    }

    private void scheduleTaskCancellation(UUID taskId) {
        scheduler.schedule(() -> cancelTask(taskId), getTaskTimeoutInstant());
    }

    @Override
    public void cancelTask(UUID taskId) {
        mutex.lock();
        Task task = getTaskById(taskId);
        if (task.getStatus() != TaskStatus.IN_PROGRESS) {
            return;
        }
        task.setStatus(TaskStatus.ERROR);
        taskPersistencePort.save(task);
        mutex.unlock();
    }

    private Instant getTaskTimeoutInstant() {
        return Instant.now().plusMillis(taskConfigurationProperties.getMaxExecutionDuration().toMillis());
    }

    @Override
    public Task getTaskById(UUID id) {
        return taskPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id.toString()));
    }

    @Override
    public void updateTaskResultsBy(UUID taskId, int partIndex, List<String> resultWords) {
        mutex.lock();
        Task task = getTaskById(taskId);
        if (task.getStatus() != TaskStatus.IN_PROGRESS) {
            return;
        }
        if (task.getCompletedPartsIndexes().contains(partIndex)) {
            return;
        }
        task.getData().addAll(resultWords);
        task.getCompletedPartsIndexes().add(partIndex);
        if (task.getPartsNumber() == task.getCompletedPartsIndexes().size()) {
            task.setStatus(TaskStatus.READY);
        }
        taskPersistencePort.save(task);
        mutex.unlock();
    }
}
