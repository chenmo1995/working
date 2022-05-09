package com.fudn.juc.easy;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author fdn
 * @since 2022-02-10 22:01
 */
public class DeadLock {
    @Data
    static class Book {
        int money;
        String name;
    }

    public static void main(String[] args) {
        Book a = new Book();
        Book b = new Book();
        new Thread(() -> {
            synchronized (a) {
                System.out.println("线程 t1 启动：b.money" + b.money);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    b.setMoney(100);
                }
                System.out.println("线程 t1 结束：b.money" + b.money);
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println("线程 t2 启动：a.money" + a.money);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    a.setMoney(100);
                }
                System.out.println("线程 t2 结束：a.money" + a.money);
            }
        }, "t2").start();
    }
}
