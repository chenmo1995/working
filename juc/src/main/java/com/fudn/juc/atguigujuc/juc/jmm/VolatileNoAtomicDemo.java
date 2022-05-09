package com.fudn.juc.atguigujuc.juc.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zzyy
 * @create 2021-03-17 14:59
 */
class MyNumber {
    //     volatile int number = 0;
    AtomicInteger number = new AtomicInteger(0);

    public void addPlusPlus() {
        number.incrementAndGet();
        number.compareAndSet(0, 1);
    }
}

/**
 * @author fudn
 */
public class VolatileNoAtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + myNumber.number);
    }
}

