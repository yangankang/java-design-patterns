package com.myself.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixPool {
    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
        // service.submit()
        // service.execute();
        // service.invokeAll()
    }
}
