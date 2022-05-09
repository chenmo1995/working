package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-25 10:08
 */
public class Test0225 {

    /**
     * @param num1
     * @param num2
     * @return
     */
    public String complexNumberMultiply(String num1, String num2) {
        String[] a = num1.split("\\+");
        String[] b = num2.split("\\+");
        int a1 = Integer.parseInt(a[0]);
        int b1 = Integer.parseInt(b[0]);
        int a2 = Integer.parseInt(a[1].substring(0, a[1].length() - 1));
        int b2 = Integer.parseInt(b[1].substring(0, b[1].length() - 1));
        int c1 = a1 * b1 - a2 * b2;
        int c2 = a1 * b2 + a2 * b1;
        return c1 + "+" + c2 + "i";
    }

    public static void main(String[] args) {
        String[] split = "1+2".split("\\+");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(String.valueOf(-1));
    }
}
