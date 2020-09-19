package com.myself.lock;

public class SynchronizedDemo implements Runnable {
    public static void main(String[] args) {
        new Thread(new SynchronizedDemo()).start();
    }

    public void run() {
        this.method();
        this.method();
    }

    public synchronized void method() {
        synchronized (this) {
            synchronized (this) {
                System.out.println("A");
                Thread.yield();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
