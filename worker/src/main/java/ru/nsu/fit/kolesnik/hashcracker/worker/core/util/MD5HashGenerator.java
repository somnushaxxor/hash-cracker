package ru.nsu.fit.kolesnik.hashcracker.worker.core.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Component
public class MD5HashGenerator {
    public String generateHashFrom(String word) {
        return DigestUtils.md5DigestAsHex(word.getBytes(StandardCharsets.UTF_8));
    }
}
