package ru.itsjava.coffee_machine.services;

import ru.itsjava.coffee_machine.domain.Coffee;

public interface CoffeeService {

    void showCoffeeBar();
    double checkCoffeeCost(double payment, Coffee coffee);
    Coffee getCoffeeByIndex(int coffeeIndex);
}
