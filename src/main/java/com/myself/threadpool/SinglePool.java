package com.myself.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SinglePool {
    private static ExecutorService service = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
    }
}
