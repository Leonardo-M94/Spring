package ru.itsjava;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("farmer")    // Аннотация указывает на создание бина с id="farmer"
//@RequiredArgsConstructor
public class KindFarmer {
    private final Animal animal;

//    @Autowired  // Аннотация указывает на внедрение зависимых бинов.
    public KindFarmer(@Qualifier("piglet") Animal animal) {
        this.animal = animal;
    }

    public void hi() {
        System.out.println("I say hi to animal");
        animal.say();
    }
}
