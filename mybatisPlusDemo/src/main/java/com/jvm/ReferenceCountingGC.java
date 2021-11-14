package com.jvm;

/**
 * 测试引用计数法
 *
 * @author fdn
 * @since 2021-09-04 16:35
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员的属性唯一的意义就是占点内存，以便在GC日志中可以看到是否被回收过
     */
    private byte[] bigSize = new byte[_1MB * 2];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
