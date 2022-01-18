package ru.itsjava.annotations.homework;

import lombok.AllArgsConstructor;
import lombok.ToString;

@University(department = "Biology")
@AllArgsConstructor
@ToString
public class BiologyStudent {
    private final String name;
    private final String surname;
    private short course;
}
