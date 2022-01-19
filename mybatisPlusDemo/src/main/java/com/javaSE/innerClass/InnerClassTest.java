package com.javaSE.innerClass;

import java.util.function.Function;

/**
 * @author fdn
 * @since 2021-11-22 17:25
 */
public class InnerClassTest {
    public static void main(String[] args) {
        Function<Object, String> f = new Function<Object, String>() {
            @Override
            public String apply(Object obj) {
                return obj.toString();
            }
        };
    }
}
