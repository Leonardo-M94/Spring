package ru.itsjava.exceptions;

public class ResourceExhaustionException extends RuntimeException {
    public ResourceExhaustionException(String message) {
        super(message);
    }
}
