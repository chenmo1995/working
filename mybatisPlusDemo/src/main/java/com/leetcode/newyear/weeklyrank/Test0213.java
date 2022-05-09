package com.leetcode.newyear.weeklyrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 第 280 周周赛
 *
 * @author fdn
 * @since 2022-02-13 10:18
 */
public class Test0213 {
    /**
     * @param num1
     * @param num2
     * @return
     */
    int test(int num1, int num2) {
        int count = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
            count++;
        }
        return count;
    }

    /**
     * 6005. 使数组变成交替数组的最少操作数
     * 分别求出奇数位和偶数位上出现次数最多的数：x，y
     * - 如果是两个不同的数，直接返回 n - x - y
     * - 如果是两个相同的数，就需要加入出现次数第二多的数了（题目要求是交替数组，所以奇数位和偶数位上的数不能相同）
     *
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        // <num, count>
        Map<Integer, Integer> even = new HashMap<>(16);
        Map<Integer, Integer> odd = new HashMap<>(16);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even.put(nums[i], even.getOrDefault(nums[i], 0) + 1);
            } else {
                odd.put(nums[i], odd.getOrDefault(nums[i], 0) + 1);
            }
        }
        int x = getMax(even);
        int y = getMax(odd);
        int xCount;
        int yCount;
        xCount = even.get(x);
        if (y != -1) {
            yCount = odd.get(y);
        } else {
            return 0;
        }
        if (x != y) {
            return n - xCount - yCount;
        } else {
            even.remove(x);
            odd.remove(y);
            int nx = getMax(even);
            int ny = getMax(odd);
            int nxCount;
            int nyCount;
            if (nx == -1) {
                nxCount = 0;
            } else {
                nxCount = even.get(nx);
            }
            if (ny == -1) {
                nyCount = 0;
            } else {
                nyCount = odd.get(ny);
            }
            return n - Math.max(xCount + nyCount, nxCount + yCount);
        }
    }

    /**
     * 求 value 最大的 key
     *
     * @param map
     * @return
     */
    private int getMax(Map<Integer, Integer> map) {
        int count = 0;
        int key = -1;
        for (Integer integer : map.keySet()) {
            if (map.get(integer) > count) {
                count = map.get(integer);
                key = integer;
            }
        }
        return key;
    }

    public static void main(String[] args) {
        Test0213 test0213 = new Test0213();
        test0213.minimumOperations(new int[]{3,1,3,2,4,3});
    }

    /**
     * 6006. 拿出最少数目的魔法豆
     * 任意一个数（a）作为基准，比他小的需要变为0，比他大的需要变成 a
     * 如果暴力，复杂度为 n2，会超时吧
     * <p>
     * 以谁作为基准可以用二分法，比 a 小的数做基准，结果都比他大，比 a 大的数做基准，结果也比他大
     *
     * @param beans
     * @return
     */
    int[] beans;

    public long minimumRemoval(int[] beans) {
        this.beans = beans;
        int n = beans.length;
        Arrays.sort(beans);
        if (n == 1) {
            return 0;
        }
        // <num,count>
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < n; i++) {
            map.put(beans[i], map.getOrDefault(beans[i], 0) + 1);
        }
        long[] sums = new long[n];
        sums[0] = getSum(beans[0]);
        long min = sums[0];
        for (int i = 1; i < n; i++) {
            if (beans[i] == beans[i - 1]) {
                sums[i] = sums[i - 1];
            } else {
                sums[i] = sums[i - 1] - (long) (beans[i] - beans[i - 1]) * (n - i) + (long) beans[i - 1] * map.get(beans[i - 1]);
            }
            min = Math.min(min, sums[i]);
        }
        return min;
    }

    private long getSum(int standard) {
        long sum = 0;
        for (int bean : beans) {
            if (bean >= standard) {
                sum += bean - standard;
            } else {
                sum += bean;
            }
        }
        return sum;
    }

    public long minimumRemoval2(int[] beans) {
        Arrays.sort(beans);
        if (beans[0] == beans[beans.length - 1]) return 0;
        long sum = 0;
        for (int i = 0; i < beans.length; i++) sum += beans[i];
        long res = sum;
        for (int i = 0; i < beans.length; i++)
            res = Math.min(sum - (long) beans[i] * (long) (beans.length - i), res);
        return res;
    }
}
