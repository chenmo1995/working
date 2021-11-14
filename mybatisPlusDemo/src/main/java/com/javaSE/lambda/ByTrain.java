package com.javaSE.lambda;

/**
 * 策略类-->坐火车
 * @author fdn
 * @since 2021-08-12 15:11
 */
public class ByTrain implements MyRunnable{
    /**
     * run方法
     */
    @Override
    public void run() {
        System.out.println("去12306买了一张票");
        System.out.println("坐火车出行。。。。");
    }
}
