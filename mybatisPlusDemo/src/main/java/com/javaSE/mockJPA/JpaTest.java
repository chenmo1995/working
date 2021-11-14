package com.javaSE.mockJPA;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author fdn
 * @since 2021-08-23 22:26
 */
public class JpaTest {
    public static void main(String[] args) {
        new B();
    }
}

class A<T> {
    public A() {
        System.out.println(this);
        Class<? extends A> clazz = this.getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);

        System.out.println(genericSuperclass);
        System.out.println("***************"+genericSuperclass.getClass());
//        System.out.println("A的构造方法");
    }
}


class B extends A<String> {
}

class C extends A<Integer> {
}

