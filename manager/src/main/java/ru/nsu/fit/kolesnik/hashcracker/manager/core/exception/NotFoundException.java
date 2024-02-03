package ru.nsu.fit.kolesnik.hashcracker.manager.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // FIXME
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
