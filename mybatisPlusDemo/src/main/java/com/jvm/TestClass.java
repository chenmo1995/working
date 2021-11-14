package com.jvm;

/**
 * @author fdn
 * @since 2021-09-04 23:00
 */
public class TestClass {
    private int m;
    private static String a = new String("1");
    private static TestClass b = new TestClass();
    public int inc() {
        return m + 1;
    }
}
