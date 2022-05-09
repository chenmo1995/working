package com.fudn.juc.atguigujuc.first.cf;

/**
 * @author fdn
 * @since 2022-04-15 13:11
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
