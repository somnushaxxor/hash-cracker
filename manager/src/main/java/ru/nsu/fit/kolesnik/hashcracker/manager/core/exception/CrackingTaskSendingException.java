package ru.nsu.fit.kolesnik.hashcracker.manager.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) // FIXME
public class CrackingTaskSendingException extends RuntimeException {
    public CrackingTaskSendingException(Throwable cause) {
        super("Error occurred while sending cracking tasks to workers", cause);
    }
}
