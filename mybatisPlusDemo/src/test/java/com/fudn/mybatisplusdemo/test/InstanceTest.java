package com.fudn.mybatisplusdemo.test;


/**
 * <P>Java 判断一个类是否是另一个类的子孙类<P>
 *
 * <li>1、判断一个对象是否是某个类的子孙类的对象时，
 * 可以直接使用  a instanceof B，或者 B.class.isAssignableFrom(a.getClass());</li>
 *
 * <li>2、没有对象时，判断一个类是否是另一个类的子孙类，
 * 只能使用 B.class.isAssignableFrom(C.class);</li>
 *
 * @author fdn
 * @since 2021-09-23 16:01
 */
public class InstanceTest {
    public static void main(String[] args) {
        A a = new C();
        B b = new C();
        C c = new C();


        System.out.println(a instanceof B); // true
        System.out.println(b instanceof B); // true
        System.out.println(c instanceof B); // true

        //这结果奇奇怪怪的
        System.out.println(A.class.isInstance(B.class)); // false
        System.out.println(B.class.isInstance(B.class)); // false
        System.out.println(C.class.isInstance(B.class)); // false

        System.out.println(A.class.isAssignableFrom(B.class)); // true
        System.out.println(B.class.isAssignableFrom(B.class)); // true
        System.out.println(C.class.isAssignableFrom(B.class)); // false
    }
}
class A {}

class B extends A {}

class C extends B {}