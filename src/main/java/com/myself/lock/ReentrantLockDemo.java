package com.myself.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    // 公平锁
    // private static ReentrantLock lock = new ReentrantLock(true);
    private String name;

    public ReentrantLockDemo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
//        lock.lock();
//        lock.unlock();
        new Thread(new ReentrantLockDemo("A")).start();
        new Thread(new ReentrantLockDemo("B")).start();
        new Thread(new ReentrantLockDemo("C")).start();
    }

    @Override
    public void run() {
        try {
            lock.lock();
            // Condition condition = lock.newCondition();
            // condition.await();
            // condition.signal();

            System.out.println(this.name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
