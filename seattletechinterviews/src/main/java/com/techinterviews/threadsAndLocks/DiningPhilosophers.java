package com.techinterviews.threadsAndLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xeniah on 3/8/15.
 */
public class DiningPhilosophers {
    public static class Chopstick
    {
        private Lock lock;
        public String name;

        public Chopstick(String name)
        {
            this.lock = new ReentrantLock();
            this.name = name;
        }

        public void pickUp()
        {
            this.lock.lock();
        }

        public void putDown()
        {
            this.lock.unlock();;
        }
    }

    public static class Philosopher extends Thread
    {
        public int bites = 10;
        public Chopstick leftChopstick;
        public Chopstick rightChopstick;
        public String name;

        public Philosopher(String name, Chopstick rightChopstick, Chopstick leftChopstick)
        {
            this.rightChopstick = rightChopstick;
            this.leftChopstick = leftChopstick;
            this.name = name;
        }

        public void eat()
        {
            pickUp();
            chew();
            putDown();
        }

        private void pickUp() {
            this.leftChopstick.pickUp();
            System.out.println(this.name + " picked up his left chopstick ...");
            this.rightChopstick.pickUp();
            System.out.println(this.name + " picked up his right chopstick ...");
        }

        private void chew() {
            System.out.println(this.name + " is chewing ...");
        }

        private void putDown() {
            this.leftChopstick.putDown();
            System.out.println(this.name + " put down his left chopstick ...");
            this.rightChopstick.putDown();
            System.out.println(this.name + " put down his right chopstick ...");
        }

        public void run()
        {
            for(int i = 0; i < 1000000; i++)
            {
                eat();
            }
        }
    }

    public static  void printPhilosophers(List<Philosopher> philosophers)
    {
        for(Philosopher p : philosophers){
            System.out.println("Philosopher " + p.name + " has chopsticks " + p.leftChopstick.name + " and " + p.rightChopstick.name);
        }
    }
    public static void main(String[] args) {
        int numPhil = 6;
        List<Philosopher> philosophers = new ArrayList<Philosopher>(numPhil);
        List<Chopstick> chopsticks = new ArrayList<Chopstick>();
        for(int i = 0; i < numPhil; i++) {
            chopsticks.add(new Chopstick(i+""));
        }

        for(int i = 0; i < numPhil; i++)
        {
            String n = (char)('A' + i) + "";
            philosophers.add(new Philosopher(n, chopsticks.get((i+1)%numPhil), chopsticks.get(i)));
            philosophers.get(i).run();
        }


    }
}
