package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-14 08:41
 */
public class Test0214 {

    /**
     * 540. 有序数组中的单一元素
     * 这不就是二分吗
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        // 一共奇数个数，n/2 分成两部分之后：左边比右边多一个数
        // 如果左边是奇数个数：
        //      - if left == right,目标在右边
        //      - if left != right,目标在左边
        // 如果左边是偶数个数：
        //      - if left == right,目标在左边
        //      - if left != right,目标在右边
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int left = nums[mid];
            int right = nums[mid + 1];

            if (mid % 2 == 0) {
                // 左边奇数个
                if (left == right) {
                    // 目标在右边，并且不是 right
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }else {
                if (left == right) {
                    // 目标在左边，并且不是 left
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

        }
        return nums[l];
    }
}
