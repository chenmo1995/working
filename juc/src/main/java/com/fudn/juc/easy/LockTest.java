package com.fudn.juc.easy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fdn
 * @since 2021-12-07 23:19
 */
public class LockTest {
    public static void main(String[] args) {
        Apple2 apple = new Apple2(50, "red");

        int num = 30;
        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                apple.subtract();
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                apple.subtract();
            }
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                apple.subtract();
            }
        }, "t3").start();
    }
}

@Data
@Slf4j
class Apple2 {
    private int number;
    private String color;

    public Apple2(int number,String color){
        this.number = number;
        this.color = color;
    }

    Lock lock = new ReentrantLock();
    public void subtract() {

        lock.lock();
        try {
            if (number > 0) {
                log.info("线程{}进来了，还剩{}个苹果", Thread.currentThread().getName(), number);
                number -= 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}