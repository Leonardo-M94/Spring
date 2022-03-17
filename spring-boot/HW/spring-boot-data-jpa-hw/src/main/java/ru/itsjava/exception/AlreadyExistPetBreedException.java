package ru.itsjava.exception;

public class AlreadyExistPetBreedException extends RuntimeException {
    public AlreadyExistPetBreedException(String message) {
        super(message);
    }
}
