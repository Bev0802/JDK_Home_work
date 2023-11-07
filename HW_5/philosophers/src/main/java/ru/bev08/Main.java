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

        ArrayList<Thread> philosopher = new ArrayList<>();
        Eating eating = new Eating();
        eating.addFork();        

        philosopher.add(new Thread(new Philosopher(eating, 1, 2)));
        philosopher.add(new Thread(new Philosopher(eating, 2, 3)));
        philosopher.add(new Thread(new Philosopher(eating, 3, 4)));
        philosopher.add(new Thread(new Philosopher(eating, 4, 5)));
        philosopher.add(new Thread(new Philosopher(eating, 5, 1)));
       
        philosopher.forEach(Thread::start);
        
    }
}