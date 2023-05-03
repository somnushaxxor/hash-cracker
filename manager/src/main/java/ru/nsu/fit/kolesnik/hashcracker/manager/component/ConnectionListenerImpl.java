package ru.nsu.fit.kolesnik.hashcracker.manager.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.model.CrackingTask;
import ru.nsu.fit.kolesnik.hashcracker.manager.producer.CrackingTaskProducer;
import ru.nsu.fit.kolesnik.hashcracker.manager.repository.CrackingTaskRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ConnectionListenerImpl implements ConnectionListener {

    private final CrackingTaskRepository crackingTaskRepository;
    private final CrackingTaskProducer crackingTaskProducer;
    private final ConnectionFactory connectionFactory;

    @PostConstruct
    private void postConstruct() {
        connectionFactory.addConnectionListener(this);
    }

    @Override
    public void onCreate(Connection connection) {
        List<CrackingTask> crackingTasks = crackingTaskRepository.findAll();
        for (CrackingTask crackingTask : crackingTasks) {
            crackingTaskProducer.produce(crackingTask.getManagerRequest());
            crackingTaskRepository.delete(crackingTask);
        }
    }

}
