package com.fudn.juc.atguigujuc.first.cf;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author fdn
 * @since 2022-04-15 13:00
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread run");
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Thread run");
            }
        }, "t1").start();


        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                return "hello";
            }
        });
        new Thread(futureTask, "t2").start();
    }
}
