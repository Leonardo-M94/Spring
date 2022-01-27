package ru.itsjava;

public class KindFarmer {
    private final Animal animal;

    public KindFarmer(Animal animal) {
        this.animal = animal;
    }

    public void hi() {
        System.out.println("I say hi to animal");
        animal.say();
    }
}
