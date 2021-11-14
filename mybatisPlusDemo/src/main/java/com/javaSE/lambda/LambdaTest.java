package com.javaSE.lambda;

import java.util.concurrent.TimeUnit;

/**
 * @author fdn
 * @since 2021-08-12 16:54
 */
public class LambdaTest {
    public static void main(String[] args) throws InterruptedException {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.testClosure();
    }

    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";
        // 构造一个匿名内部类对象
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(str);
//                System.out.println("this========>"+this);
//            }
//        };
        Runnable r = () -> {
            System.out.println(str);
            System.out.println("this========>" + this);
        };
        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }
}
