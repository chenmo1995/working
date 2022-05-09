package com.leetcode.december;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-12-27 08:39
 */
public class Test1227 {

    /**
     * 825. 适龄的朋友
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length;
        int result = 0;
        // [left, right)
        for (int i = 0, left = 0, right = 0; i < n; i++) {
            if (ages[i] < 14) {
                continue;
            }
            // left 是第一个满足 ages[left] > ages[i] * 0.5 + 7 的
            while (left < i && ages[left] <= ages[i] * 0.5 + 7) {
                left++;
            }
            // right 是第一个 ages[right + 1] > ages[i] 的
            while (right + 1 < n && ages[right + 1] <= ages[i]) {
                right++;
            }
            result += right - left;
        }
        return result;
    }

    /**
     * 825. 适龄的朋友
     *
     * @param ages
     * @return
     */
    public int numFriendRequests2(int[] ages) {
        // 年龄范围很小，也就是 ages 数组中值的范围很小
        // 使用计数的思想
        int[] count = new int[121];
        for (int i : count) {
            // count[i] 表示年龄为 i 的数的个数
            count[i]++;
        }
        // 前缀和
        int[] preSum = new int[121];
        preSum[0] = count[0];
        for (int i = 1; i < count.length; i++) {
            preSum[i] = preSum[i - 1] + count[i];
        }

        int result = 0;
        // 找到   0.5*y + 7 < x <= y 中人的个数        后续添加：这样计算出来不好，应该一个一个遍历过来
        // 所以 count[i] 表示年龄为 i 的人数，preSum[right] - preSum[left] 表示的区间 (left,right] 的总人数,刚好满足条件
        // ages[i] 添加好友的个数就是 count[i] * (preSum[right] - preSum[left])
        // -1 表示去掉自己
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {

            }
        }

        return result;
    }

    boolean check(int x, int y) {
        if (y <= 0.5 * x + 7) return false;
        if (y > x) return false;
        if (y > 100 && x < 100) return false;
        return true;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(1);
//        list.add(1);
//        list.add(1);
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int a = (int) (0.5 * 5);
        System.out.println(a);
        System.out.println(collect);
    }
}
