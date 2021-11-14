package com.javaSE.lambda;

/**
 * @author fdn
 * @since 2021-08-12 15:12
 */
public class ByAirPlane implements MyRunnable{
    /**
     * run方法
     */
    @Override
    public void run() {
        System.out.println("在某App上订了飞机票");
        System.out.println("坐飞机出行。。。");

    }
}
