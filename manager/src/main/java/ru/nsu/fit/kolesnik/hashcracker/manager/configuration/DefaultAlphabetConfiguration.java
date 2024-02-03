package ru.nsu.fit.kolesnik.hashcracker.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.fit.kolesnik.hashcracker.manager.core.model.Alphabet;

import java.util.HashSet;
import java.util.Set;

@Configuration
class DefaultAlphabetConfiguration {
    @Bean
    Alphabet defaultAlphabet() {
        // Generating alphabet that contains lowercase latin letters and digits
        final Set<Character> characters = new HashSet<>(36);
        for (char digitCode = '0'; digitCode <= '9'; digitCode++) {
            characters.add(digitCode);
        }
        for (char letterCode = 'a'; letterCode <= 'z'; letterCode++) {
            characters.add(letterCode);
        }
        return new Alphabet(characters);
    }
}
