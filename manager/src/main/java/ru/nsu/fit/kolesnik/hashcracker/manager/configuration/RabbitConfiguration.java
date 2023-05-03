package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MarshallingMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class RabbitConfiguration {

    @Value("${tasks.queue.name}")
    private String tasksQueueName;
    @Value("${task-results.queue.name}")
    private String taskResultsQueueName;

    @Bean
    public MessageConverter marshallingMessageConverter(Jaxb2Marshaller jaxb2Marshaller) {
        final MarshallingMessageConverter marshallingMessageConverter = new MarshallingMessageConverter();
        marshallingMessageConverter.setMarshaller(jaxb2Marshaller);
        marshallingMessageConverter.setUnmarshaller(jaxb2Marshaller);
        return marshallingMessageConverter;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory,
                                     MessageConverter marshallingMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(marshallingMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue tasksQueue() {
        return new Queue(tasksQueueName, true);
    }

    @Bean
    public Queue taskResultsQueue() {
        return new Queue(taskResultsQueueName, true);
    }

}
