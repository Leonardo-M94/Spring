package ru.itsjava.annotations.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyJUnit myJUnit = new MyJUnit(new MyFavoriteTest());
        myJUnit.start();

    }
}
