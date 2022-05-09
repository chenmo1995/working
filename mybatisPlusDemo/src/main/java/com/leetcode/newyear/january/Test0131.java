package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-02-08 11:44
 */
public class Test0131 {
    /**
     * 1342. 将数字变成 0 的操作次数
     *
     * @param num
     * @return
     */
    public int numberOfSteps2(int num) {
        int count = 0;
        while (num > 0) {
            num = num % 2 == 0 ? num / 2 : num - 1;
            count++;
        }
        return count;
    }

    public int numberOfSteps(int num) {
        // 每一次操作要么是将 num 右移一位，要么是将 num 的最后一位变成 0；
        // 直到最后 num == 0
        // 所以要求的是 num 中的最高位是第几位，以及 num 中有多少位为 1
        int a = getLoc(num);
        int b = getCount(num);
        return Math.max(a - 1 + b, 0);
    }

    private int getCount(int num) {
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if ((num >> i & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    private int getLoc(int num) {
        for (int i = 31; i >= 0; i--) {
            if ((num >> i & 1) == 1) {
                return i + 1;// 第几位
            }
        }
        return -1;
    }
}
