package com.juc.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fdn
 * @since 2021-12-02 00:34
 */
@Slf4j
public class TestAtomic {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        boolean b = atomicInteger.compareAndSet(0, 1);
        log.info("值是：{}", atomicInteger.get());
    }

    private static void arrays() {
        int[] ints = new int[]{1, 2, 3};
        int[] ints1 = new int[3];
        int[] ints2 = {1, 2, 3};
    }
}
