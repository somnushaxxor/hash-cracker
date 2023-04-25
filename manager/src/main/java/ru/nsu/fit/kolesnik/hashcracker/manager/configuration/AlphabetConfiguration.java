package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AlphabetConfiguration {

    @Bean
    public List<String> alphabet() {
        // Generating alphabet that contains lowercase latin letters and digits
        final List<String> alphabet = new ArrayList<>();
        for (char digitCode = 48; digitCode <= 57; digitCode++) {
            alphabet.add(String.valueOf(digitCode));
        }
        for (char letterCode = 97; letterCode <= 122; letterCode++) {
            alphabet.add(String.valueOf(letterCode));
        }
        return alphabet;
    }

}
