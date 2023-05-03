package ru.nsu.fit.kolesnik.hashcracker.manager.consumer;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.nsu.fit.kolesnik.hashcracker.manager.service.CrackingRequestService;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = {"${task-results.queue.name}"})
public class CrackingTaskResultConsumer {

    private final CrackingRequestService crackingRequestService;

    @RabbitHandler
    public void consume(CrackingTaskWorkerResponse workerResponse, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        crackingRequestService.updateCrackingRequestResultsBy(workerResponse);
        channel.basicAck(tag, false);
    }

}
