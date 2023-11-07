package ru.bev08;

public class Philosopher implements Runnable {

    Eating eating;
    int fork_left;
    int fork_right;

    public Philosopher(Eating eating, int fork_left, int fork_right) {
        this.eating = eating;
        this.fork_left = fork_left;
        this.fork_right = fork_right;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eating.getFork(fork_left, fork_right);
    }

    public Eating getEating() {
        return eating;
    }

    public int getFork_left() {
        return fork_left;
    }

    public int getFork_right() {
        return fork_right;
    }
}