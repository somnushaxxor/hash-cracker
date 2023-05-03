package ru.nsu.fit.kolesnik.hashcracker.worker.consumer;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.service.CrackingTaskService;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = {"${tasks.queue.name}"})
public class CrackingTaskConsumer {

    private final CrackingTaskService crackingTaskService;

    @RabbitHandler
    public void consume(CrackingTaskManagerRequest managerRequest, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        crackingTaskService.executeCrackingTask(managerRequest);
        // TODO executeCrackingTask runs async (instant acking problem)
        channel.basicAck(tag, false);
    }

}
