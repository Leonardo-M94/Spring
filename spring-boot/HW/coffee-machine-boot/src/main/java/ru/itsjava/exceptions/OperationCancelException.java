package ru.itsjava.exceptions;

public class OperationCancelException extends RuntimeException {
    public OperationCancelException(String message) { super(message); }
}
