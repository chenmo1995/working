package com.jvm.classloader;

/**
 * @author fdn
 * @since 2021-10-29 16:18
 */
public class Cat {
    public Cat(){
        System.out.println("Cat is loaded by" + this.getClass().getClassLoader());
    }
}
