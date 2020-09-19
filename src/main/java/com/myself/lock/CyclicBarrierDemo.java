package com.myself.lock;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }
}

class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000); //以睡眠来模拟线程需要预定写入数据操作
            System.out.println("线程 " + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
