package ru.bev08;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
    private final int id;
    private final Object leftFork;
    private final Object rightFork;
    private int mealsEaten;
    
    

    public Philosopher(int id, Object leftFork, Object rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mealsEaten = 0;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Философ " + id + ": " + action);
        Thread.sleep((int) (Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                doAction("размышляет");
                DiningPhilosophers.getDiningSem().acquire(); // Получить семафор, ограничивающий количество философов, принимающих пищу одновременно.
                synchronized (leftFork) {
                    doAction("взял левую вилку");
                    synchronized (rightFork) {
                        doAction("взял правую вилку и ест");
                        mealsEaten++;
                        doAction("отложил правую вилку");

                        // Output the state of forks
                        DiningPhilosophers.printForkState();

                        // Output the number of meals eaten
                        System.out.println("Философ " + id + " поел " + mealsEaten + " раз");
                    }
                    doAction("отложил левую вилку. Вернулся к размышлениям");
                }
                DiningPhilosophers.getDiningSem().release(); // Отпустите семафор после окончания приема пищи
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}