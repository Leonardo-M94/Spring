package ru.itsjava.services;

import ru.itsjava.domain.Coffee;

public interface CoffeeService {

    void showCoffeeBar();
    double checkCoffeeCost(double payment, Coffee coffee);
    Coffee getCoffeeByIndex(int coffeeIndex);
}
