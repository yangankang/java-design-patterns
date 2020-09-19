package com.myself.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachePool {
    private static ExecutorService service = Executors.newCachedThreadPool();

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
    }
}
