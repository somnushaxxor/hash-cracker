package ru.nsu.fit.kolesnik.hashcracker.worker.core.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class MD5HashGenerator {
    private static final String MD5_ALGORITHM_NAME = "MD5";

    private MD5HashGenerator() {
    }

    public static String generateHashFrom(String word) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(MD5_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unknown hashing algorithm", e);
        }
        messageDigest.update(word.getBytes(StandardCharsets.UTF_8));
        return new String(messageDigest.digest(), StandardCharsets.UTF_8).toLowerCase(Locale.ROOT);
    }
}
