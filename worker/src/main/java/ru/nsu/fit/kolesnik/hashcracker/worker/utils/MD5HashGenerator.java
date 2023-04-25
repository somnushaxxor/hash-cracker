package ru.nsu.fit.kolesnik.hashcracker.worker.utils;

import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class MD5HashGenerator {

    private static final String HASHING_ALGORITHM_NAME = "MD5";

    private MD5HashGenerator() {
    }

    public static String generateHashFrom(String word) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unknown hashing algorithm", e);
        }
        messageDigest.update(word.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(messageDigest.digest()).toLowerCase(Locale.ROOT);
    }

}
