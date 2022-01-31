package ru.itsjava.coffee_machine.exceptions;

public class InvalidCoffeeIndexException extends RuntimeException {
    public InvalidCoffeeIndexException(String message) {
        super(message);
    }
}