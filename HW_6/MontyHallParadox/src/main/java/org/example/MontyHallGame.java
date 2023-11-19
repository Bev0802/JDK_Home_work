package org.example;

import java.util.List;
import java.util.Random;

public class MontyHallGame {
    private static final int TOTAL_TESTS = 1000;

    public static void main(String[] args) {
        GameStatistics gameStatistics = new GameStatistics();

        for (int i = 1; i <= TOTAL_TESTS; i++) {
            GameRound gameRound = new GameRound();
            playMontyHall(gameRound, gameStatistics, i);
        }

        gameStatistics.displayStatistics();
    }

    private static void playMontyHall(GameRound gameRound, GameStatistics gameStatistics, int i) {
        List<String> doors = gameRound.getDoors();

        // Участник делает начальный выбор
        int initialChoice = new Random().nextInt(3);

        // Монти открывает дверь с козлом
        int montyOpens;
        do {
            montyOpens = new Random().nextInt(3);
        } while (montyOpens == initialChoice || doors.get(montyOpens).equals("car"));

        // Получаем оставшуюся дверь после initialChoice и montyOpens
        int remainingChoice = gameRound.getRemainingDoor(initialChoice, montyOpens);

        // Обновляем статистику
        gameStatistics.updateStatistics(gameRound, initialChoice, remainingChoice);

        // Выводим результат текущей игры
        System.out.println("Игра № " + i + ":");
        System.out.println("Список дверей: " + doors);
        System.out.println(
                "Результат первого выбора: дверь № " + (initialChoice + 1) + " - " + (doors.get(initialChoice)));
        System.out.println("Дверь, открытая Монти №" + (montyOpens + 1) + " - " + (doors.get(montyOpens)));
        System.out.println(
                "Результат при выборе другой двери № " + (remainingChoice + 1) + " - " + doors.get(remainingChoice));
        System.out.println();
    }
}
