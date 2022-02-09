package ru.itsjava.exceptions;

// Ввод несуществующего индекса кофе.
public class InvalidCoffeeIndexException extends RuntimeException {
    public InvalidCoffeeIndexException(String message) {
        super(message);
    }
}