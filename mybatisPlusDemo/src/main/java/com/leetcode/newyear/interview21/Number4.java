package com.leetcode.newyear.interview21;

/**
 * 一道可以考察「二分」本质的面试题
 *
 * @author fdn
 * @since 2022-01-26 17:32
 */
public class Number4 {

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (r - l + 1 >> 1) + l;
            int midVal = nums[mid];
            if (midVal < nums[0]) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        // 最高点
        int k = l;
        if (target >= nums[0]) {
            l = 0;
            r = k;
        } else {
            l = k + 1;
            r = nums.length - 1;
        }
        while (l < r) {
            // 第一处
            int mid = (r - l >> 1) + l;
            int midVal = nums[mid];
            // 第二处
            if (midVal >= target) {
                r = mid;
            } else {
                // 第三处，这三处之间是有联系的，不能乱取。第一步和第三步的联系我已经知道了，还需要知道第二步什么时候取等号，什么时候不取等号。应该说是等号合并在哪里
                l = mid + 1;
            }
        }
        // 这最后有可能 l 已经做过了 +1 ,导致 l 越界了。必须是用 r
        return nums[r] == target ? r : -1;
    }

    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        // 第一次「二分」：找旋转点
        // 由于第一段满足 >=nums[0]，第二段不满足 >=nums[0]，当使用 >=nums[0] 进行二分，二分出的是满足此性质的最后一个数
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        // 通过和 nums[0] 进行比较，得知 target 是在旋转点的左边还是右边
        if (target >= nums[0]) {
            l = 0;
        } else {
            l = l + 1;
            r = n - 1;
        }
        // 第二次「二分」：找 target
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return nums[r] == target ? r : -1;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] arr = new int[]{1, 3};
        Number4 number4 = new Number4();
//        number4.search(arr, 0);
        number4.search2(arr, 0);
    }


}
