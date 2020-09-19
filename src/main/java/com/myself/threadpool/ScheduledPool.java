package com.myself.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledPool {
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        }, 10, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        }, 10, TimeUnit.SECONDS);
    }
}
