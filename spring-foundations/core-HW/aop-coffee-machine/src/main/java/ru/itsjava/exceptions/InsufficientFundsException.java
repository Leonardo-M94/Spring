package ru.itsjava.exceptions;

// Недостаточно суммы для совершения покупки.
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
