package ru.bev08;
/* 
Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. 
Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары, 
а также переопределение метода toString(), возвращающее строковое представление пары.*/

public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Екатерина", 41);
        Pair<Integer, Double> pair2 = new Pair<>(41, 164.51);
        
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());

        System.out.println(pair);
        System.out.println(pair2);
    }
}