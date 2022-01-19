package com.javaSE.innerClass;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fdn
 * @since 2021-11-22 14:18
 */
public class InnerClass {
    public static void main(String[] args) throws Exception {
        String a = "111";
        String b = a;
        b = "222";
        System.out.println(a);
        System.out.println(b);

        Person p1 = new Person();
        p1.setName("111");
        Person p2 = p1;
        p2.setName("222");
        System.out.println(p1);
        System.out.println(p2);

        Person p3 = new Person();
        p3.setName("333");
        p1 = p3;
        System.out.println(p1);
    }

    private static void inner() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Runnable runnable = () -> {
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("离大谱");
            }
        };
        Runnable r3 = r2.getClass().newInstance();
        Method run = r2.getClass().getMethod("run");
        run.invoke(r3, null);

        InnerClass innerClass = new InnerClass() {
        };
    }


}

@Data
class Person {
    String name;
}
