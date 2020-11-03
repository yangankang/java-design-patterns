package com.myself.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本实例演示读锁的升级
 */
public class ReentrantReadWriteDowngradeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void downgrade() {
        System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到了写锁");
            System.out.println(Thread.currentThread().getName() + "降级获取读锁");
            readLock.lock();
            try {

            } finally {
                readLock.unlock();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteDowngradeDemo reentrantReadWriteUpgardeDemo = new ReentrantReadWriteDowngradeDemo();
        new Thread(() -> reentrantReadWriteUpgardeDemo.downgrade(), "线程1").start();
        new Thread(() -> reentrantReadWriteUpgardeDemo.downgrade(), "线程2").start();

    }
}
