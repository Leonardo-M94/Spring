package ru.itsjava.exceptions;

// Отмена операции клиентом.
public class OperationCancelException extends RuntimeException {
    public OperationCancelException(String message) { super(message); }
}
