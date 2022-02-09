package ru.itsjava.exceptions;

// В автомате закончились ингридиенты.
public class ResourceExhaustionException extends RuntimeException {
    public ResourceExhaustionException(String message) { super(message); }
}
