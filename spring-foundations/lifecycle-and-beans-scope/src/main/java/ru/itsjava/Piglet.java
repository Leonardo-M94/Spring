package ru.itsjava;

import org.springframework.stereotype.Component;

@Component
public class Piglet implements Animal{

    public Piglet() {
        System.out.println("Constructor Piglet");
    }

    @Override
    public void say() {
        System.out.println("Hru-Hru");
    }
}
