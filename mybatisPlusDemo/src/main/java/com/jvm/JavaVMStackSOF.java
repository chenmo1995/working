package com.jvm;

/**
 * 虚拟机栈和本地方法栈测试
 * <p>
 * VM Args： -Xss128k
 * <p>
 * 由于HotSpot虚拟机中并不区分虚拟机栈和本地方法栈， 因此对于HotSpot来说， -Xoss参数（设置
 * 本地方法栈大小） 虽然存在， 但实际上是没有任何效果的， 栈容量只能由-Xss参数来设定。
 * <p>
 * HotSpot虚拟机不支持栈的动态扩展， 所以除非在创建线程申请内存时就因无法获得足够内存而出现
 * OutOfMemoryError异常， 否则在线程运行时是不会因为扩展而导致内存溢出的， 只会因为栈容量无法
 * 容纳新的栈帧而导致StackOverflowError异常。
 *
 * @author fdn
 * @since 2021-09-04 17:44
 */
public class JavaVMStackSOF {

    public static void main(String[] args) throws Throwable {
//        test1();
        try {
            test2();
        } catch (Error e) {
            System.out.println("stack length:" + stackLength2);
            throw e;
        }
    }

    /**
     * 使用-Xss参数减少栈内存容量。
     * 结果： 抛出StackOverflowError异常， 异常出现时输出的堆栈深度相应缩小。
     */
    private static void test1() {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

    private int stackLength = 1;
    private static int stackLength2 = 0;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * 定义了大量的本地变量， 增大此方法帧中本地变量表的长度。
     *
     * 为了多占局部变量表空间， 笔者不得
     * 不定义一长串变量
     */
    public static void test2() {
        long unused1, unused2, unused3, unused4, unused5,
                unused6, unused7, unused8, unused9, unused10,
                unused11, unused12, unused13, unused14, unused15,
                unused16, unused17, unused18, unused19, unused20,
                unused21, unused22, unused23, unused24, unused25,
                unused26, unused27, unused28, unused29, unused30,
                unused31, unused32, unused33, unused34, unused35,
                unused36, unused37, unused38, unused39, unused40,
                unused41, unused42, unused43, unused44, unused45,
                unused46, unused47, unused48, unused49, unused50,
                unused51, unused52, unused53, unused54, unused55,
                unused56, unused57, unused58, unused59, unused60,
                unused61, unused62, unused63, unused64, unused65,
                unused66, unused67, unused68, unused69, unused70,
                unused71, unused72, unused73, unused74, unused75,
                unused76, unused77, unused78, unused79, unused80,
                unused81, unused82, unused83, unused84, unused85,
                unused86, unused87, unused88, unused89, unused90,
                unused91, unused92, unused93, unused94, unused95,
                unused96, unused97, unused98, unused99, unused100;
        stackLength2++;
        test2();
        unused1 = unused2 = unused3 = unused4 = unused5 =
                unused6 = unused7 = unused8 = unused9 = unused10 =
                        unused11 = unused12 = unused13 = unused14 = unused15 =
                                unused16 = unused17 = unused18 = unused19 = unused20 =
                                        unused21 = unused22 = unused23 = unused24 = unused25 = unused26 = unused27 = unused28 = unused29 = unused30 =
                                                unused31 = unused32 = unused33 = unused34 = unused35 =
                                                        unused36 = unused37 = unused38 = unused39 = unused40 =
                                                                unused41 = unused42 = unused43 = unused44 = unused45 =
                                                                        unused46 = unused47 = unused48 = unused49 = unused50 =
                                                                                unused51 = unused52 = unused53 = unused54 = unused55 =
                                                                                        unused56 = unused57 = unused58 = unused59 = unused60 =
                                                                                                unused61 = unused62 = unused63 = unused64 = unused65 =
                                                                                                        unused66 = unused67 = unused68 = unused69 = unused70 =
                                                                                                                unused71 = unused72 = unused73 = unused74 = unused75 =
                                                                                                                        unused76 = unused77 = unused78 = unused79 = unused80 =
                                                                                                                                unused81 = unused82 = unused83 = unused84 = unused85 =
                                                                                                                                        unused86 = unused87 = unused88 = unused89 = unused90 =
                                                                                                                                                unused91 = unused92 = unused93 = unused94 = unused95 =
                                                                                                                                                        unused96 = unused97 = unused98 = unused99 = unused100 = 0;


    }
}
