package com.leetcode.newyear.interview21.tooffer;

/**
 * @author fdn
 * @since 2022-03-22 17:17
 */
public class Test0322 {

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * TODO 还没做出来
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int n = numbers.length;
        int l = 0, r = n - 1;
        // 需要找到第一个 < numbers[0] 的值；
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = numbers[mid];
            // midVal == numbers[l] 可能在左边可能在右边,无法二分
            if (midVal < numbers[l]) {
                r = mid;
            } else if (midVal > numbers[l]) {
                l = mid + 1;
            } else {
                l++;
            }
            // 最后找到的是 <= numbers[0] 的第一个值；
        }
        return numbers[l];
    }
}
