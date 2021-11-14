package com.javaSE.ThreadTest;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author fdn
 * @since 2021-10-24 21:15
 */
@Getter
public class ThreadTest extends Thread{
    private boolean running = true;
    private int count = 0;

    @Override
    public void run() {
        while (running) {
            count++;
             System.out.println("在运行");
        }
    }

    private void stopRunning() {
        this.running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest thread = new ThreadTest();
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.stopRunning();
        System.out.println(thread.getCount());
        System.out.println("子线程终止");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(thread.getCount());
    }
}
