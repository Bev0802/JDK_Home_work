package ru.bev08;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Eating {
    private ArrayList<Lock> forks;
    private PhilosopherList philosopherList;

    Eating(PhilosopherList philosopherList) {
        this.philosopherList = philosopherList;
        this.forks = new ArrayList<>(philosopherList.getPhilosophers().size());

        for (int i = 0; i < philosopherList.getPhilosophers().size(); i++) {
            forks.add(new ReentrantLock());
        }
    }

    void addFork() {
        forks.add(new ReentrantLock());
    }

    public void getFork(int left, int right, String philosopherName) {
        Lock leftFork = forks.get(left - 1);
        Lock rightFork = forks.get(right - 1);

        while (true) {
            try {
                if (leftFork.tryLock()) {
                    try {
                        if (rightFork.tryLock()) {
                            System.out.println(philosopherName + " берёт вилки под номером: " + left + " и " + right);
                            System.out.println("Статус вилок пока философ ест: " + forksStatus());
                            eat(philosopherName);
                            System.out.println(philosopherName + " поел.");
                            System.out.println("Статус вилок: " + forksStatus());
                            return;
                        }
                    } finally {
                        rightFork.unlock();
                    }
                }
            } finally {
                leftFork.unlock();
            }
        }
    }

    private String forksStatus() {
        StringBuilder status = new StringBuilder("{");
        for (int i = 0; i < forks.size(); i++) {
            status.append((i + 1)).append("=").append(forks.get(i).tryLock() ? 1 : 0);
            if (i < forks.size() - 1) {
                status.append(", ");
            }
            forks.get(i).unlock();  // Разблокируем вилку после проверки состояния
        }
        status.append("}");
        return status.toString();
    }

    void eat(String philosopherName) {
        System.out.println(philosopherName + " поел.");
        Philosopher philosopher = philosopherList.getPhilosopherByName(philosopherName);
        if (philosopher != null) {
            philosopher.eat();
        }
    }

    PhilosopherList getEating() {
        return philosopherList;
    }
}
