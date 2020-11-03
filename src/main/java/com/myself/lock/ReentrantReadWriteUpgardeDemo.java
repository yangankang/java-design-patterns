package com.myself.lock;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本实例演示读锁的升级
 */
public class ReentrantReadWriteUpgardeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void upgarde() {
        System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到了读锁");
            System.out.println(Thread.currentThread().getName() + "阻塞获取写锁");
            writeLock.lock();

        } finally {
            readLock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "锁结束");
    }

    public static void main(String[] args) throws IOException {
        ReentrantReadWriteUpgardeDemo reentrantReadWriteUpgardeDemo = new ReentrantReadWriteUpgardeDemo();
        new Thread(() -> reentrantReadWriteUpgardeDemo.upgarde(), "线程1").start();
        new Thread(() -> reentrantReadWriteUpgardeDemo.upgarde(), "线程2").start();
//        ServerSocket serverSocket =  new ServerSocket(0); //读取空闲的可用端口
//        int port = serverSocket.getLocalPort();
//        System.out.println(port);
    }
}
