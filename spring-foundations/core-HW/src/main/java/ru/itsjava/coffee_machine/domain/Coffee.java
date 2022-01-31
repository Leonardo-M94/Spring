package ru.itsjava.coffee_machine.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Coffee {

    AMERICANO(35.0), LATTE(50.0), CAPPUCCINO(50.0), ESPRESSO(30.0), CHOCOLATE(35.0);

    private final double price;

    @Override
    public String toString() {
        return this.name() + ": " + this.price;
    }

    public double getPrice() {
        return price;
    }
}
