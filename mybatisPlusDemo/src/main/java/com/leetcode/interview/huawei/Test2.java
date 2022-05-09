package com.leetcode.interview.huawei;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fdn
 * @since 2022-04-28 20:30
 */
public class Test2 {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                System.out.println("A");
                conditionB.signal();
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A");
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                System.out.println("B");
                conditionC.signal();
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                System.out.println("C");
                conditionA.signal();
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C");
        t3.start();

    }

    /**
     * 最少移除 s 中多少个字符，使得 p 是 s 的子串
     * <p>
     * dp[i][j] 表示 s[0,i] p[0,j] s 移除的最少字符个数
     * <p>
     * dp[i + 1][j + 1]
     * - s[i + 1] == p[j + 1]       dp[i][j]
     * - s[i + 1] != p[j + 1]
     *
     * @param s
     * @param p
     * @return
     */
    int remove(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m < n) {
            return -1;
        }


        return 0;

    }

}
