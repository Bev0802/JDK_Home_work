package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameRound {
    private List<String> doors;

    public GameRound() {
        // Инициализация дверей
        doors = new ArrayList<>();
        doors.add("goat");
        doors.add("goat");
        doors.add("car");
        // Перемешиваем двери в случайном порядке
        Collections.shuffle(doors);
    }

    public List<String> getDoors() {
        return doors;
    }

    public int getRemainingDoor(int initialChoice, int montyOpens) {
        for (int i = 0; i < doors.size(); i++) {
            if (i != initialChoice && i != montyOpens) {
                return i;
            }
        }
        return -1; // В случае ошибки, возвращаем -1 или можно выбрать другой способ обработки
    }
}

