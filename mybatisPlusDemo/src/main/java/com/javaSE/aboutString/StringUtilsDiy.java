package com.javaSE.aboutString;


import com.javaSE.lambda.stream.Person;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2021-09-08 09:57
 */
public class StringUtilsDiy {
    public static void main(String[] args) {
        String a = "123456";
        String[] split = a.split("_\\|");
        System.out.println(Arrays.asList(split));


        // exceptionTest();
//        timeTest();
//        newString();
    }

    static int m;
    int n;
    void testStatic(){
        System.out.println(m);
        System.out.println(n);
    }

    private static void newString() {
        String a;
        String b = new String();
        Person p = new Person();
        System.out.println(b.equals(""));
        System.out.println(p);
        System.out.println(m);
//        System.out.println(a);
    }

    private static void timeTest() {
        long l = System.currentTimeMillis();
        System.out.println(l);
    }


    private static void exceptionTest() {
        try {
            try {
                int[] nums = new int[3];
//                int i = nums[4];
                if (true) {
                    throw new RuntimeException("出错了333");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("出错了2222");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("出错了1111");
        }
    }
}
