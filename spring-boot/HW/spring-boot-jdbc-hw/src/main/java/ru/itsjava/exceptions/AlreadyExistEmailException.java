package ru.itsjava.exceptions;

public class AlreadyExistEmailException extends RuntimeException {
    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
