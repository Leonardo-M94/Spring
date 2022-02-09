package ru.itsjava.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // Аннотация указывает на класс Аспект
@Component
public class LoggingAspect {

    @Before("execution(* ru.itsjava.services.FilmService.*(..))") // Точка среза (pointcut) определяет место в коде для применения совета
    public void logBefore(JoinPoint joinPoint) { // Совет (advice) до вызова методов FilmService
        System.out.println("Log Before " + joinPoint.getTarget().getClass().getName());
    }

    @After("execution(* ru.itsjava.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Log After " + joinPoint.getTarget().getClass().getName());
    }

    @Around("execution(* ru.itsjava.services.*.*(..))") // Совет-обёртка, должен возвращать тип оборачиваемого метода и вызывать его напрямую.
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Log Around Before " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
        Object result = proceedingJoinPoint.proceed(); // Обязательный вызов основного метода, иначе он не отработает.
        System.out.println("Log Around After " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
        return result;
    }

}
