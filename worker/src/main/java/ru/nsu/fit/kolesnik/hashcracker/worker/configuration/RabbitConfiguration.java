package ru.nsu.fit.kolesnik.hashcracker.worker.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitConfiguration {
    @Value("${task-parts.queue.name}")
    private String taskPartsQueueName;
    @Value("${task-parts-results.queue.name}")
    private String taskPartsResultsQueueName;

    @Bean
    public MessageConverter marshallingMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory,
                                     MessageConverter marshallingMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(marshallingMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue taskPartsQueue() {
        return new Queue(taskPartsQueueName, true);
    }

    @Bean
    public Queue taskPartsResultsQueue() {
        return new Queue(taskPartsResultsQueueName, true);
    }
}
