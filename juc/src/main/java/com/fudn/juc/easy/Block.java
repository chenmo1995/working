package com.fudn.juc.easy;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fdn
 * @since 2021-12-07 23:44
 */
@Slf4j
public class Block {

    public static void main(String[] args) {

        Block block = new Block();

        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.info("同步代码块执行完毕");
        }, "t1").start();

        new Thread(() -> {

            log.info("线程2获得apple对象锁");

        }, "t2").start();
    }

    private static void syncBlock() {
        Apple apple = new Apple();

        new Thread(() -> {
            synchronized (apple) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("同步代码块执行完毕");
        }, "t1").start();

        new Thread(() -> {
            synchronized (apple) {
                log.info("线程2获得apple对象锁");
            }
        }, "t2").start();
    }


}
