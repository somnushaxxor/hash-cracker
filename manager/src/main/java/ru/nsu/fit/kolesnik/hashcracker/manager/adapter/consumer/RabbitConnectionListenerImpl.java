package ru.nsu.fit.kolesnik.hashcracker.manager.adapter.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.service.TaskService;

@RequiredArgsConstructor
@Component
public class RabbitConnectionListenerImpl implements ConnectionListener {
    private final TaskService taskService;
    private final ConnectionFactory connectionFactory;

    @PostConstruct
    private void postConstruct() {
        connectionFactory.addConnectionListener(this);
    }

    @Override
    public void onCreate(Connection connection) {
        taskService.getCreatedTasks().forEach(task -> taskService.produceTask(task.getId()));
    }
}
