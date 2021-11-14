package com.javaSE.enumeration.mock;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2021-08-31 10:28
 */
public class TestFinal {
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        final String[] values = {"1", "2", "3"};
        System.out.println("修改前" + Arrays.asList(values));
        values[2] = "change";
        System.out.println("修改后" + Arrays.asList(values));
    }

    private static void test2(){
        final String[] values = {"1", "2", "3"};
        System.out.println("修改前" + Arrays.asList(values));
        String[] clone = values.clone();
        System.out.println(values==clone);//==  比较的是内存地址
        System.out.println(Arrays.equals(values,clone));
        System.out.println(values.getClass()==clone.getClass());



        clone[1] = "change";
        System.out.println("修改后" + Arrays.asList(values));
        System.out.println("修改后" + Arrays.asList(clone));
        System.out.println(Arrays.equals(values,clone));
    }
}
