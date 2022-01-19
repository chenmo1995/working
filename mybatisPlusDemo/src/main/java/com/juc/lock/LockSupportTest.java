package com.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fdn
 * @since 2021-11-23 23:32
 */
@Slf4j
public class LockSupportTest {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.State state = Thread.currentThread().getState();
            log.info("t1线程的状态：{}", state);
        }, "t1");


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("t1线程的状态：{}", t1.getState());

        Thread t2 = new Thread(() -> {
            log.info("t2线程的状态lllll：{}", Thread.currentThread().getState());
            LockSupport.park();
            Thread.State state = Thread.currentThread().getState();
        }, "t2");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("t2线程的状态：{}", t2.getState());
        log.info("t1线程的状态：{}", t1.getState());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private static void testLockSupport() {
        Thread t1 = new Thread(() -> {
            log.info("线程{}进来了", Thread.currentThread().getName());
            LockSupport.park();
            LockSupport.park();
            log.info("线程{}被重新唤醒", Thread.currentThread().getName());
        }, "t1");
        t1.start();

        new Thread(() -> {
            log.info("线程{}启动", Thread.currentThread().getName());
            LockSupport.unpark(t1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(t1);
        }, "t2").start();
    }
}
