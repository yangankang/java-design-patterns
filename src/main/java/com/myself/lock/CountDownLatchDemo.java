package com.myself.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static final CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("count down A");
                latch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("count down B");
                latch.countDown();
            }
        }).start();
        System.out.println("Start await");
        latch.await();
        System.out.println("Finish await");
    }
}
