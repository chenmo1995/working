package com.leetcode.newyear.interview21;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-02-28 12:55
 */
public class Number8 {
    /**
     * 7. 整数反转
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            if (x > 0 && ans > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            if (x < 0 && ans < (Integer.MIN_VALUE - x % 10) / 10) {
                return 0;
            }
            // -15 % 10 = -5
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    /** items index for next take, poll, peek or remove */
    private int num;

    public static void main(String[] args) {
        int a = -16 % 7;// 取余，往 0 方向取商，结果为 -2
        Math.floorMod(-16, 7);// 取模，往无穷小方向取商，结果为 5


        Number8 number8 = new Number8();
        number8.reverse(-150);

        System.out.println(Arrays.toString(new int[]{1, 2, 3}));
        System.out.println(Arrays.asList("1", "2", "3").size());
        System.out.println(Arrays.asList(1, 2, 3).size());
        System.out.println(Arrays.asList(new Integer[]{1, 2, 3}).size());

        String c = new String("111");
        String d = new String("111");
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());

        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));
    }
}
