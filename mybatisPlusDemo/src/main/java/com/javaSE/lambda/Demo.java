package com.javaSE.lambda;

/**
 * @author fdn
 * @since 2021-08-12 15:05
 */
public class Demo {
    public static void main(String[] args) {
        new MyThread(new ByTrain()).start();
        new MyThread(new ByAirPlane());
        new MyThread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("我走路出行。。。");
            }
        }).start();
        new MyThread(()->{
            System.out.println("我颅内出行。。。");
        }).start();
    }
}
