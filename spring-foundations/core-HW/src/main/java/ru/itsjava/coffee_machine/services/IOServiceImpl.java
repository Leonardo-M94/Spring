package ru.itsjava.coffee_machine.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IOServiceImpl implements IOService {

    private final BufferedReader bufferedReader;

    public IOServiceImpl() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @SneakyThrows
    @Override
    public String read() {
        return bufferedReader.readLine();
    }
}
