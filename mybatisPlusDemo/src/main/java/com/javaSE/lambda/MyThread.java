package com.javaSE.lambda;

/**
 * @author fdn
 * @since 2021-08-12 15:04
 */
public class MyThread {

    private MyRunnable target;

    public MyThread(MyRunnable target) {
        this.target = target;
    }

    public void run() {
        System.out.println("去12306买了一张票");
        System.out.println("坐火车...");
    }

    /**
     * 如果外部定义了出行策略，就应该调用外部的出行策略
     */
    public void start() {
        if (target == null) {
            run();
        }else {
            target.run();
        }
    }

}
