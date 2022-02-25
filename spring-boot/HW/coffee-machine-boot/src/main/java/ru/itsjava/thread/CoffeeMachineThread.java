package ru.itsjava.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itsjava.exceptions.ResourceExhaustionException;
import ru.itsjava.services.CoffeeMachine;

@Component("coffeeThread")
@RequiredArgsConstructor
public class CoffeeMachineThread extends Thread {
    private final CoffeeMachine coffeeMachine;

    @Override
    public void run() {
        try {
            System.out.println("-> Welcome!");

            while ((coffeeMachine.isResourcesEnough())) {
                coffeeMachine.coffeeTransaction();
            }
        } catch (ResourceExhaustionException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
