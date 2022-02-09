package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.InsufficientFundsException;
import ru.itsjava.exceptions.InvalidCoffeeIndexException;
import ru.itsjava.exceptions.OperationCancelException;
import ru.itsjava.exceptions.ResourceExhaustionException;

@Service("coffeeMachine")
@RequiredArgsConstructor
public class CoffeeMachineImpl implements CoffeeMachine {

    private final CoffeeService coffeeService;
    private final IOService ioService;
    private int resources = 2;    // Условно... На определенное количество чашек.

    @Override
    public boolean coffeeTransaction() {

        Coffee selectedCoffee = null;
        double payment = 0;
        boolean enoughMoney = true;

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
            } catch (OperationCancelException exception) {
                System.out.println("Warning: " + exception.getMessage());
                return false;
            }
        }

        resources--;
        System.out.println("-> Your " + selectedCoffee.name() + " is ready.\n");
        return true;
    }

    public boolean isResourcesEnough() throws RuntimeException {
        if (resources > 0)
            return true;

        throw new ResourceExhaustionException("Resources have run out.\nIt is required to refuel the coffee machine.");
    }

}
