package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Email {
    private long id;
    private final String email;
    private final String password;
    private final String fio;
    private final Date birthday;
    private final Boolean male;
    private final Pet pet;
}
