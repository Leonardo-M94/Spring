package ru.itsjava.services;

import org.springframework.stereotype.Service;
import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.InsufficientFundsException;
import ru.itsjava.exceptions.InvalidCoffeeIndexException;
import ru.itsjava.exceptions.OperationCancelException;


@Service
public class CoffeeServiceImpl implements CoffeeService {

    @Override
    public Coffee getCoffeeByIndex(int coffeeIndex) throws RuntimeException {

        if (coffeeIndex == 0) {
            throw new OperationCancelException("Coffee operation cancel.");
        }

        if ((coffeeIndex > Coffee.values().length) ||
                (coffeeIndex < 0)) {
            throw new InvalidCoffeeIndexException("Invalid coffee index!");
        }

        return Coffee.values()[coffeeIndex - 1];
    }


    @Override
    public void showCoffeeBar() {

        for (Coffee coffee : Coffee.values()) {
            System.out.println(coffee.ordinal() + 1 + ") " + coffee.toString());
        }
        System.out.println("0) CANCEL OPERATION");
    }

    @Override
    public double checkCoffeeCost(double payment, Coffee coffee) throws RuntimeException {

        double change = payment - coffee.getPrice();

        if (change < 0) {
            throw new InsufficientFundsException("Not enough money.");
        }

        return change;
    }
}
