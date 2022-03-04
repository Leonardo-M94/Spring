package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private final String fio;
    private final Date birthday;
    private final Boolean male;
    private final Email email;
    private final Pet pet;
}
