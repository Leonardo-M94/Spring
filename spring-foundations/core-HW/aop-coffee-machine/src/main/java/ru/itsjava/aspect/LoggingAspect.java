package ru.itsjava.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Around("execution(* ru.itsjava.services.CoffeeMachine.coffeeTransaction())")
    public Object logCoffeeTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Start new " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Transaction completed: " + result.toString());
        return result;
    }

    @Pointcut("execution(* ru.itsjava.services.CoffeeService.*(..))")
    public void coffeeServiceMethods() {
    }


    @AfterThrowing(pointcut = "coffeeServiceMethods()", throwing = "exception")
    public void logServiceException(JoinPoint joinPoint, RuntimeException exception) {
        logger.warn(
                "Exception = \"" + exception.getMessage() + "\"\n" +
                        "From " + joinPoint.toShortString() + "\n" +
                        "with args: " + Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(pointcut = "@annotation(ru.itsjava.annotation.Marker)", returning = "change")
    public void logServiceGetChange(double change) {
        if (change > 0) {
            logger.info("Give out change: " + change);
        }
    }

    @AfterThrowing(pointcut = "execution(* ru.itsjava.services.CoffeeMachine.*(..))", throwing = "exception")
    public void logExhaustionResources(RuntimeException exception) {
        logger.error(exception.getMessage());
    }

}
