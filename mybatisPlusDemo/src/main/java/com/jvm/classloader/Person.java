package com.jvm.classloader;

/**
 * @author fdn
 * @since 2021-10-29 16:18
 */
public class Person {
    public Person(){
        System.out.println("Person is loaded by" + this.getClass().getClassLoader());
        new Cat();
    }
}
