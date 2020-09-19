package com.myself.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable {
    private static Semaphore semaphore = new Semaphore(5);
    private String name;

    public SemaphoreDemo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        new Thread(new SemaphoreDemo("A")).start();
        new Thread(new SemaphoreDemo("B")).start();
        new Thread(new SemaphoreDemo("C")).start();
        new Thread(new SemaphoreDemo("E")).start();
        new Thread(new SemaphoreDemo("F")).start();
        new Thread(new SemaphoreDemo("G")).start();
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (semaphore != null) semaphore.release();
        }
    }
}
