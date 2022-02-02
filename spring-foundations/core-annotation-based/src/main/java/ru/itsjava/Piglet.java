package ru.itsjava;

import org.springframework.stereotype.Component;

@Component  // Аннотация указывает на создание бина с id = "piglet".
public class Piglet implements Animal{

    @Override
    public void say() {
        System.out.println("Hru-Hru");
    }
}
