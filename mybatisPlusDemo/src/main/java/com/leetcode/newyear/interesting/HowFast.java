package com.leetcode.newyear.interesting;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试下计算机 1s 内能执行多少次操作
 * 本机的主频 1.1GHz，目前工作的频率是 3.0GHz（睿频）
 *
 * @author fdn
 * @since 2022-02-17 18:48
 */
@Slf4j
public class HowFast {
    public static void main(String[] args) {
        long N = 20000;
        long startTime = System.currentTimeMillis();
//        timeN(N);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        log.info("O(n) 算法，输入n:{} ，耗时:{}ms", N, time);

        startTime = System.currentTimeMillis();
        timeNSquare(N);
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        log.info("O(n^2) 算法，输入n:{} ，耗时:{}ms", N, time);

//        startTime = System.currentTimeMillis();
//
//        timeNLogN(N);
//        endTime = System.currentTimeMillis();
//        time = endTime - startTime;
//        log.info("O(nlogn) 算法，输入n:{} ，耗时:{}ms", N, time);
    }

    private static void timeNLogN(long n) {
        long k = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 1; j < n; j *= 2) {
                k++;
            }
        }
    }

    private static void timeNSquare(long n) {
        long k = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < n; j++) {
                k++;
            }
        }
    }

    private static void timeN(long n) {
        long k = 0;
        for (long i = 0; i < n; i++) {
            k++;
        }
    }

}
