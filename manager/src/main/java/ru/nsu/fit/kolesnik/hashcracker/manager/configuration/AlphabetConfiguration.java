package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
class AlphabetConfiguration {
    @Bean
    Set<Character> alphabet() {
        // Generating alphabet that contains lowercase latin letters and digits
        final Set<Character> alphabet = new HashSet<>(36);
        for (char digitCode = 48; digitCode <= 57; digitCode++) {
            alphabet.add(digitCode);
        }
        for (char letterCode = 97; letterCode <= 122; letterCode++) {
            alphabet.add(letterCode);
        }
        return alphabet;
    }
}
