package ru.bev;

/*Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: 
sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа 
(но необязательно разного между собой), над которыми должна быть произведена операция. */

public class Main {
    public static void main(String[] args) {
        double resalt = Calculator.divide(3, 3.5);
        System.out.println(resalt);
        resalt = Calculator.multiply(1, 1.5);
        System.out.println(resalt);
        resalt = Calculator.subtract(1, 1.5);
        System.out.println(resalt);
        resalt = Calculator.sum(1, 1.5);
        System.out.println(resalt);
    }
}