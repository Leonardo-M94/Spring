package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.InsufficientFundsException;
import ru.itsjava.exceptions.InvalidCoffeeIndexException;

@RequiredArgsConstructor
public class CoffeeMachineImpl implements CoffeeMachine {

    private final CoffeeService coffeeService;
    private final IOService ioService;

    @Override
    public void coffeeTransaction() {

        Coffee selectedCoffee = null;
        double payment = 0;
        boolean enoughMoney = true;

        System.out.println("-> Welcome, dear coffee lover!");

        while (true) {
            try {
                if ((payment == 0) || (!enoughMoney)) {
                    System.out.println("-> Please, insert the bill: ");
                    payment += Double.parseDouble(ioService.read());
                }

                if (selectedCoffee == null) {
                    System.out.println("-> Choose coffee index: ");
                    coffeeService.showCoffeeBar();

                    selectedCoffee = coffeeService.getCoffeeByIndex(
                            Integer.parseInt(ioService.read())
                    );
                }

                double change = coffeeService.checkCoffeeCost(payment, selectedCoffee);

                if (change > 0) {
                    System.out.println("-> Take your change: " + change);
                }
                break;

            } catch (InvalidCoffeeIndexException exception) {
                System.out.println("Error: " + exception.getMessage());
            } catch (InsufficientFundsException exception) {
                System.out.println("Error: " + exception.getMessage());
                enoughMoney = false;
            }
        }

        System.out.println("-> Your " + selectedCoffee.name() + " is ready.");
        System.out.println("-> Be careful, the drink is hot!");
    }
}
