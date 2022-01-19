package com.fudn.juc.easy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fdn
 * @since 2021-12-07 23:03
 */
@Slf4j
public class SynchronizedTest {
    public static void main(String[] args) {
        Apple apple = new Apple(50, "red");
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
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
class Apple {
    private int number;
    private String color;

    public synchronized void subtract() {
        if (number>0){
            log.info("线程{}进来了，还剩{}个苹果",Thread.currentThread().getName(),number);
            number -= 1;
        }
    }
}
