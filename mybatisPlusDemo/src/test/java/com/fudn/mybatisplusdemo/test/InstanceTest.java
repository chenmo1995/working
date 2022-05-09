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

        A a1 = new A();


        /**
         * 同 isInstance
         */
        System.out.println(a instanceof B); // true
        System.out.println(b instanceof B); // true
        System.out.println(c instanceof B); // true
        System.out.println(c instanceof A); // true
        System.out.println(c instanceof C); // true
        System.out.println(a1 instanceof C); // false

        //这结果奇奇怪怪的
        /**
         * true if {@code obj} is an instance of this class
         * 参数是个对象，判断参数中对象是否是调用者的实例，这几个都是 Class 类的对象，肯定返回 false 了
         */
        System.out.println(A.class.isInstance(B.class)); // false
        System.out.println(B.class.isInstance(B.class)); // false
        System.out.println(C.class.isInstance(B.class)); // false
        System.out.println(C.class.isInstance(C.class)); // false
        System.out.println(C.class.isInstance(c)); // true
        System.out.println(Class.class.isInstance(c)); // false
        System.out.println(Class.class.isInstance(C.class)); // true

        /**
         * whether objects of the type {@code cls} can be assigned to objects of this class
         * 参数中的类是否可以由调用者的类派生过来
         */
        System.out.println(A.class.isAssignableFrom(B.class)); // true
        System.out.println(B.class.isAssignableFrom(B.class)); // true
        System.out.println(C.class.isAssignableFrom(B.class)); // false
    }
}
class A {}

class B extends A {}

class C extends B {}