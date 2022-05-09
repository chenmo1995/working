package com.leetcode.december;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-20 10:03
 */
public class Test1220 {

    /**
     * 475. 供暖器
     * 贪心？
     * <p>
     * <p>
     * 遍历houses，找到距离每个house最近的左右各一个最近的heater，取最小距离
     * 然后在所有的最小距离中找到最大的距离，即时能满足全部house的供暖范围了
     * <p>
     * 答案具有二分的特性，小于R，不满足，大于R，就满足
     * 二分法来试，给定一个R，符合就缩小，直到找到不能再缩小的时候
     * 所以难点落在了如何校验符不符合，也就是check方法的编写
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int l = 0, r = (int) 1e9;
        while (l < r) {
            int mid = l + r >> 1;
            // 0~max，按照符合题意与否进行区分，二分找到临界值
            if (check(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 退出循环的时候 l=r
        return l;
    }

    /**
     * 遍历houses，houses[i] > target + heaters[j]，说明j不行，j++，
     * 如果一直没找到 houses[i] < target + heaters[j]，就说明target不行
     * <p>
     * 光是 houses[i] < target + heaters[j] 还不行，因为最开始的时候会出现 houses[i] 很小的情况
     * 所以还得要求 heaters[j] - target < houses[i]
     *
     * @param houses
     * @param heaters
     * @param target
     * @return
     */
    private boolean check(int[] houses, int[] heaters, int target) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > target + heaters[j]) {
                j++;
            }
            // 要么j>=m了，要么找到 houses[i] <= target + heaters[j]
            // 都j>=m，肯定得返回false了，但是光 houses[i] <= target + heaters[j] 却还不能返回true
            if (j < m && heaters[j] - target <= houses[i] && houses[i] <= heaters[j] + target) {
                // 在target下，这个house符合了，继续下一层遍历
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * * 遍历houses，找到距离每个house最近的左右各一个最近的heater，取最小距离
     * * 然后在所有的最小距离中找到最大的距离，即时能满足全部house的供暖范围了
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = 0;
        int n = houses.length;
        for (int i = 0; i < n; i++) {
            // 找到同位置或者离houses[i]最近的最小heater，找不到返回-1（只有一种情况，就是houses[i]小于heater[0]）
            // 这返回的是heater中的index
            int l = binarySearch(houses[i], heaters);
            int r = l + 1;
            // 考虑 l=-1 和 r=heaters.length
            // l = -1 距离就应该 heaters[0] - houses[i]  只有右侧距离，所以设置成最大整型，取最小值的时候舍弃掉
            // r = heaters.length 距离就应该 houses[i] - heaters[heaters.length]  只有左侧距离，所以设置成最大整型，取最小值的时候舍弃掉
            int leftDistance = l == -1 ? Integer.MAX_VALUE : houses[i] - heaters[l];
            int rightDistance = r == heaters.length ? Integer.MAX_VALUE : heaters[r] - houses[i];
            int min = Math.min(leftDistance, rightDistance);
            result = Math.max(result, min);
        }
        return result;
    }

    private int binarySearch(int target, int[] heaters) {
        int l = 0, r = heaters.length - 1;
        if (target < heaters[l]) {
            return -1;
        }
        while (l < r) {
            int mid = l + r + 1 >> 1;
            int midVal = heaters[mid];
            if (target < midVal) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        int a = ~5;
        int l = 0, r = (int) 1e9;
        double v = 1e8;
        System.out.println(r);
        System.out.println(a);
        Test1220 test1220 = new Test1220();
        int radius2 = test1220.findRadius2(new int[]{1, 2, 3, 4}, new int[]{1, 4});
        System.out.println(radius2);
//        System.out.println(test1220.isIP("00"));
//        System.out.println("0123456".substring(6));
//        test1220.restoreIpAddresses("25525511135");
    }

}
