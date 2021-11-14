package com.javaSE.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-08-12 15:24
 */
public class Demo01 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";
//        int i = compareString(str1, str2, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });

//        Comparator<String> comparator = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        };
//        int i = compareString(str1, str2, comparator);
//        System.out.println(i);

        Comparator<String> comparator1 = (o1, o2) -> o1.length() - o2.length();
        int i1 = compareString(str1, str2, comparator1);
        System.out.println(i1);

        Comparator<String> comparator = Comparator.comparingInt(String::length);
        compareString(str1,str2,String::compareTo);
        int i = compareString(str1, str2, comparator);
        System.out.println(i);


        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> collect = list.stream().filter((a) -> a > 2).collect(Collectors.toList());
        System.out.println(collect);


    }

    public static int compareString(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }
}
