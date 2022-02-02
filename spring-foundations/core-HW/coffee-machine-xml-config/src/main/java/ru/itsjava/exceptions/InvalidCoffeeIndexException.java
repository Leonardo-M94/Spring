package ru.itsjava.exceptions;

public class InvalidCoffeeIndexException extends RuntimeException {
    public InvalidCoffeeIndexException(String message) {
        super(message);
    }
}