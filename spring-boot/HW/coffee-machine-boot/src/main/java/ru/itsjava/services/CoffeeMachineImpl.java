package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private Coffee selectedCoffee;
    private double payment;
    private boolean enoughMoney;

    @Value("${coffee-machine.resources}")   // = 2
    private int resources;    // Объем ресурсов измеряется количеством чашек.

    @Override
    public boolean coffeeTransaction() {

        selectedCoffee = null;
        payment = 0;
        enoughMoney = true;

        while (true) {
            try {
                billAccepting();    // Прием купюр (оплата).
                coffeeSelection();  // Выбор кофе/отмена операции.
                paymentCheck();     // Проверка оплаты и сдачи.

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


    private void billAccepting() {

        if ((payment == 0) || (!enoughMoney)) {
            System.out.println("-> Please, insert the bill: ");
            payment += Double.parseDouble(ioService.read());
        }
    }


    private void coffeeSelection() throws InvalidCoffeeIndexException, OperationCancelException {

        if (selectedCoffee == null) {
            System.out.println("-> Choose coffee index: ");
            coffeeService.showCoffeeBar();

            selectedCoffee = coffeeService.getCoffeeByIndex(
                    Integer.parseInt(ioService.read())
            );
        }
    }


    private void paymentCheck() throws InsufficientFundsException {

        double change = coffeeService.checkCoffeeCost(payment, selectedCoffee);

        if (change > 0) {
            System.out.println("-> Take your change: " + change);
        }
    }


    @Override
    public boolean isResourcesEnough() throws RuntimeException {
        if (resources > 0)
            return true;

        throw new ResourceExhaustionException("Coffee resources have run out at machine.");
    }

    public void setResources(int resources) {
        if (resources >= 0 && resources <= 1000) {
            this.resources = resources;
        }
    }

    public int getResources() {
        return resources;
    }
}