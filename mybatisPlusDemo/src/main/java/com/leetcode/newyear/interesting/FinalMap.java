package com.leetcode.newyear.interesting;

import java.util.HashMap;
import java.util.Map;

/**
 * String 的 final 修饰跟 hashMap 有关没
 *
 * @author fdn
 * @since 2022-03-01 14:46
 */
public class FinalMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String a = "abc";//final String a = "abc";
        String s = new String();
        s = "111";
        s = "222";
        String b = "fudanan";
        System.out.println("hashcode:" + a.hashCode());
        map.put(a, b);
        a = "abcd";
        System.out.println("hashcode:" + a.hashCode());
        System.out.println(map.get(a));
        System.out.println(map.get("abc"));
    }
}
