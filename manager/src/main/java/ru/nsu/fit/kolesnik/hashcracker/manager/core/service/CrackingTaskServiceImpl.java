package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.exception.NotFoundException;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.persistance.CrackingTaskPersistencePort;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.port.producer.CrackingTaskPartProducerPort;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CrackingTaskServiceImpl implements CrackingTaskService {
    private final CrackingTaskPartProducerPort crackingTaskPartProducerPort;
    private final CrackingTaskPersistencePort crackingTaskPersistencePort;

    private final Set<Character> alphabet;

    // TODO
    private final TaskScheduler taskScheduler;
    @Value("${workers.number}")
    private Integer workersNumber;
    @Value("${cracking-request.execution.max-duration}")
    private Duration crackingRequestMaxExecutionDuration;

    @Override
    public CrackingTask createCrackingTask(String hash, int maxLength) {
        final int crackingTasksNumber = countCrackingTasksNumber();
        final CrackingTask crackingTask = new CrackingTask(hash, maxLength, crackingTasksNumber);
        // TODO create parts
        crackingTaskPartProducerPort.produce(crackingTask);
        // TODO ???
        crackingTaskPersistencePort.save(crackingTask);
        scheduleCrackingTaskCancellation(crackingTask.getId());
        return crackingTask;
    }

    private int countCrackingTasksNumber() {
        return workersNumber; // TODO ????
    }

    private void scheduleCrackingTaskCancellation(UUID crackingTaskId) {
        taskScheduler.schedule(() -> cancelCrackingTask(crackingTaskId), getCrackingRequestTimeoutInstant());
    }

    private void cancelCrackingTask(UUID crackingTaskId) {
//        synchronized (crackingTask) { // TODO ???
        CrackingTask crackingTask = getCrackingTaskById(crackingTaskId);
        crackingTask.setStatus(CrackingTask.Status.ERROR);
        crackingTaskPersistencePort.save(crackingTask);
    }

    private Instant getCrackingRequestTimeoutInstant() {
        return Instant.now().plusMillis(crackingRequestMaxExecutionDuration.toMillis());
    }

    @Override
    public CrackingTask getCrackingTaskById(UUID id) {
        return crackingTaskPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id.toString()));
    }

    @Override
    public void updateCrackingTaskResultsBy(UUID taskId, int partIndex, Set<String> resultWords) {
        final CrackingTask crackingTask = getCrackingTaskById(taskId);
        synchronized (crackingTask) { // TODO ?????
            if (crackingTask.getStatus() == CrackingTask.Status.IN_PROGRESS) {
                crackingTask.getData().addAll(resultWords);
                crackingTask.getCompletedTasks().add(partIndex);
                if (crackingTask.getTasksNumber() == crackingTask.getCompletedTasks().size()) {
                    crackingTask.setStatus(CrackingTask.Status.READY);
                }
                crackingTaskPersistencePort.save(crackingTask);
            }
        }
    }
}
