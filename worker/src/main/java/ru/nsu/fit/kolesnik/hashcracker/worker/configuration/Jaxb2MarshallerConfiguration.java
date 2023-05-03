package ru.nsu.fit.kolesnik.hashcracker.worker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.schema.worker.response.CrackingTaskWorkerResponse;

@Configuration
public class Jaxb2MarshallerConfiguration {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        final Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(CrackingTaskManagerRequest.class, CrackingTaskWorkerResponse.class);
        return jaxb2Marshaller;
    }

}
