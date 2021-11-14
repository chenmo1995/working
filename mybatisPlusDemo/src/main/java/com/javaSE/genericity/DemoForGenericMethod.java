package com.javaSE.genericity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-08-25 00:35
 */
public class DemoForGenericMethod {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        List<String> stringList = reverseList(list);
    }

    /**
     * 静态的泛型方法，出现在一个普通类中
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> reverseList(List<T> list) {
        List<T> newList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            newList.add(list.get(i));
        }
        return newList;
    }

}
