package ru.itsjava.services;

import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.InsufficientFundsException;
import ru.itsjava.exceptions.InvalidCoffeeIndexException;

public class CoffeeServiceImpl implements CoffeeService {

    @Override
    public Coffee getCoffeeByIndex(int coffeeIndex) throws RuntimeException {

        if ((coffeeIndex > Coffee.values().length) ||
                (coffeeIndex < 1)) {
            throw new InvalidCoffeeIndexException("Invalid coffee index!");
        }

        return Coffee.values()[coffeeIndex - 1];
    }


    @Override
    public void showCoffeeBar() {

        for (Coffee coffee : Coffee.values()) {
            System.out.println(coffee.ordinal() + 1 + ") " + coffee.toString());
        }
    }

    @Override
    public double checkCoffeeCost(double payment, Coffee coffee) {

        double change = payment - coffee.getPrice();

        if (change < 0) {
            throw new InsufficientFundsException("Not enough money.");
        }

        return change;
    }
}
