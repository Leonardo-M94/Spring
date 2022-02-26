package ru.itsjava.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
public class Email {
    private long id;
    private final String email;
    private final String password;
    private final String fio;
    private final Date birthday;
    private final Boolean male;
}
