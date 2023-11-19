package ru.bev08;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    private static Object[] forks;
    private static Semaphore diningSem;

    public static void startDiningPhilosophers() {
        int numberOfPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
        forks = new Object[numberOfPhilosophers];
        diningSem = new Semaphore(numberOfPhilosophers - 3); // Разрешить одновременное употребление пищи до (количество философов - 3) философам

        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < numberOfPhilosophers; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % numberOfPhilosophers];

            philosophers[i] = new Philosopher(i + 1, leftFork, rightFork);

            Thread philosopherThread = new Thread(philosophers[i]);
            philosopherThread.start();
        }
    }

    public static synchronized void printForkState() {
        System.out.print("Состояние вилок: ");
        for (Object fork : forks) {
            System.out.print((fork == null) ? "0 " : "1 ");
        }
        System.out.println();
    }

    public static Semaphore getDiningSem() {
        return diningSem;
    }
}
