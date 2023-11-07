package ru.bev08;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Eating extends Thread {
    private HashMap<Integer, Integer> fork = new HashMap<>();
    //public ArrayList<Thread> philosophers = new ArrayList<>();
    ReentrantLock forkLock = new ReentrantLock();
    Condition condition = forkLock.newCondition();

    Eating() {
        forkLock = new ReentrantLock();
        condition = forkLock.newCondition();

    }

    void addFork() {
        fork.put(1, 1); // 0 - вилка занята, 1 - вилака свободна.
        fork.put(2, 1);
        fork.put(3, 1);
        fork.put(4, 1);
        fork.put(5, 1);
    }

    // void addPhilosopher() {
    //     philosophers.add(new Thread(new Philosopher(this, 1, 2)));
    //     philosophers.add(new Thread(new Philosopher(this, 2, 3)));
    //     philosophers.add(new Thread(new Philosopher(this, 3, 4)));
    //     philosophers.add(new Thread(new Philosopher(this, 4, 5)));
    //     philosophers.add(new Thread(new Philosopher(this, 5, 1)));        
    // }

    void getFork(int fork_left, int fork_right) {
        forkLock.lock();        
        try {
            for (int counterEat = 1; counterEat < 4; counterEat++) {                
                if (fork.get(fork_left) == 1 && fork.get(fork_right) == 1) {
                    fork.put(fork_left, 0);
                    fork.put(fork_right, 0);
                    System.out.println("Философ-" + fork_left + " размышляет.");
                    System.out.println("Философ-" + fork_left + " берёт вилки под номером: " + fork_left + " и "
                            + fork_right + "\nСтатус вилок пока филосов ест: " + fork);
                    Thread.sleep(1000);
                    fork.put(fork_left, 1);
                    fork.put(fork_right, 1);
                    System.out.println("Философ-" + fork_left + " поел " + counterEat + " раза.\nСтатус вилок: " + fork);                    
                    condition.signalAll();
                    

                } else {
                    System.out.println("Философ-" + fork_left + " размышляет.");
                    condition.await();
                }
            }
            
        } catch (InterruptedException e) {
            System.out.println("Error");
        } finally {
            forkLock.unlock();
        }return;
        
    }

}
