package com.javaSE.redisLock;

/**
 * @author fdn
 * @since 2021-08-25 17:28
 */
public class LockDemo {
    public static void main(String[] args) {
        Son son = new Son();
        son.m();
    }
}

class Father {
    synchronized void m() {
        System.out.println("父类的同步方法m()。。。。");
    }
}

class Son extends Father {
    @Override
    synchronized void m() {
        System.out.println("子类同步方法m()。。。****");
        super.m();
        System.out.println("子类同步方法m()。。。#####");
    }
}
