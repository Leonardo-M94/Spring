package ru.itsjava;

import org.springframework.stereotype.Component;

@Component
public class Lamb implements Animal {

    @Override
    public void say() {
        System.out.println("Be-Be");
    }
}
