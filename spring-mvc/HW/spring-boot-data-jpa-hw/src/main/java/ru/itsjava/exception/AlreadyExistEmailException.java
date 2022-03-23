package ru.itsjava.exception;

public class AlreadyExistEmailException extends RuntimeException {
    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
