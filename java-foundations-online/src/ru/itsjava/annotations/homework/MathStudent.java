package ru.itsjava.annotations.homework;

import lombok.AllArgsConstructor;
import lombok.ToString;

@University(department = "Math")
@AllArgsConstructor
@ToString
public class MathStudent {
    private final String name;
    private final String surname;
    private short course;
}
