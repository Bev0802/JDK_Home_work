package ru.bev08;


import java.util.ArrayList;

public class PhilosopherList {
    private ArrayList<Philosopher> philosophers;

    PhilosopherList(ArrayList<Philosopher> philosophers) {
        this.philosophers = philosophers;
    }

    ArrayList<Philosopher> getPhilosophers() {
        return philosophers;
    }

    Philosopher getPhilosopherByName(String name) {
        for (Philosopher philosopher : philosophers) {
            if (philosopher.getPhilosopherName().equals(name)) {
                return philosopher;
            }
        }
        return null;
    }

    Philosopher getNextPhilosopher(Philosopher philosopher) {
        int currentIndex = philosophers.indexOf(philosopher);
        int nextIndex = (currentIndex + 1) % philosophers.size();
        return philosophers.get(nextIndex);
    }

    Philosopher getPrevPhilosopher(Philosopher philosopher) {
        int currentIndex = philosophers.indexOf(philosopher);
        int prevIndex = (currentIndex - 1 + philosophers.size()) % philosophers.size();
        return philosophers.get(prevIndex);
    }   
}