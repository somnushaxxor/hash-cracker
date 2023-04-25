package ru.nsu.fit.kolesnik.hashcracker.worker.exception;

public class CrackingTaskResultSendingException extends RuntimeException {

    public CrackingTaskResultSendingException(Throwable cause) {
        super("Error occurred while sending task result back to the manager", cause);
    }

}
