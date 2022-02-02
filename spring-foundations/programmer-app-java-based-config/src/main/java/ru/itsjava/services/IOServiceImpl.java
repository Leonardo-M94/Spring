package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOServiceImpl implements IOService {

    private final BufferedReader reader;

    public IOServiceImpl(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public String input() {
        return reader.readLine();
    }
}
