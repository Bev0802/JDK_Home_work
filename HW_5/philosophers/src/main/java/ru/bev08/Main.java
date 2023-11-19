/* Задача:
        Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
        Вилки лежат на столе между каждой парой ближайших философов.
        Каждый философ может либо есть, либо размышлять.
        Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
        Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
        Философ может взять только две вилки сразу, то есть обе вилки должны быть свободны
        Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
*/
package ru.bev08;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Philosopher> philosophers = new ArrayList<>();

        Eating eating = new Eating(new PhilosopherList(philosophers));

        for (int i = 0; i < 5; i++) {
            philosophers.add(new Philosopher(eating, i + 1, (i + 1) % 5 + 1, "Философ-" + (i + 1)));
        }

        ArrayList<Thread> philosopherThreads = new ArrayList<>();
        for (Philosopher philosopher : philosophers) {
            Thread thread = new Thread(philosopher);
            thread.start();
            philosopherThreads.add(thread);
        }

        // Ожидание завершения всех потоков
        for (Thread thread : philosopherThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

