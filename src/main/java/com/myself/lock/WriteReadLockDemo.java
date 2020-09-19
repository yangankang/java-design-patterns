package com.myself.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLockDemo implements Runnable {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private String name;

    public WriteReadLockDemo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
//        new Thread(new WriteReadLockDemo("A")).start();
//        new Thread(new WriteReadLockDemo("B")).start();
//        new Thread(new WriteReadLockDemo("C")).start();
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("A", 1);
        map.put("B", 1);
        map.put("A", 2);
    }

    @Override
    public void run() {
        Lock writeLock = null;
        try {
            writeLock = lock.writeLock();
            writeLock.lock();
            System.out.println(this.name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (writeLock != null) writeLock.unlock();
        }
    }
}
