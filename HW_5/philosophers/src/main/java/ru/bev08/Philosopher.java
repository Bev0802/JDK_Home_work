package ru.bev08;

class Philosopher implements Runnable {

    Eating eating;
    int fork_left;
    int fork_right;
    String name;
    int eatCounter;

    Philosopher(Eating eating, int fork_left, int fork_right, String name) {
        this.eating = eating;
        this.fork_left = fork_left;
        this.fork_right = fork_right;
        this.name = name;
        this.eatCounter = 0;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000); // Размышление
                eating.getFork(fork_left, fork_right, name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void eat() {
        eatCounter++;
    }

    Eating getEating() {
        return eating;
    }

    int getFork_left() {
        return fork_left;
    }

    int getFork_right() {
        return fork_right;
    }

    String getPhilosopherName() {
        return name;
    }

    int getEatCounter() {
        return eatCounter;
    }
}
