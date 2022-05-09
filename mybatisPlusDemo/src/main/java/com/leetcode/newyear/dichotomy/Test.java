package com.leetcode.newyear.dichotomy;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author fdn
 * @since 2022-01-27 15:58
 */
public class Test {

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return new int[]{-1, -1};
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = nums[mid];
            if (midVal >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) {
            return new int[]{-1, -1};
        }
        int num1 = l;
        l = 0;
        r = n - 1;
        while (l < r) {
            int mid = (r - l + 1 >> 1) + l;
            int midVal = nums[mid];
            if (midVal > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        int num2 = l;
        return new int[]{num1, num2};
    }


    /**
     * 35. 搜索插入位置
     * 先写着，用哪种确定边界的方法都行，写完了再来调整
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = nums[mid];
            if (midVal >= target) {
                // 最终得到的结果是满足条件的最左边的值，所以最终 l == r 走出循环，nums[l] 应该是 >= target 的第一个值
                // 正是我们要的结果。
                // 最终整个数组都没有满足条件的值，这种情况应该单独考虑，
                // 因为我们的 l == r 最多只能取到 n - 1，最少只能取到 0
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[n - 1] < target ? l + 1 : l;
    }

    /**
     * 162. 寻找峰值
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = nums[mid];
            if (midVal >= nums[mid + 1]) {// mid 取不到 n - 1
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 367. 有效的完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l < r) {
            int midVal = (r - l + 1 >> 1) + l;
            if (num / midVal < midVal) {
                r = midVal - 1;
            } else {
                l = midVal;
            }
        }
        return l * l == num;
    }

    /**
     * 911. 在线选举
     */
    class TopVotedCandidate {

        /**
         * 需要桶排序放 persons 吗，应该不需要
         * [0, 1, 2, 2, 2, 1, 0, 1]
         * [0, 1, 2, 2, 2, 2, 2, 1]
         *
         * @param persons
         * @param times
         */
        int[] times;
        int[] who;

        public TopVotedCandidate(int[] persons, int[] times) {
            // 创建一个 2 个元素的数组和一个 n 个元素的数组，分别用来排序和用来求对应时间的领先的候选人
            int n = persons.length;
            who = new int[n];
            // [count][new][person]
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
            // <person, count>
            Map<Integer, Integer> map = new HashMap<>();
            int index = 0;
            for (int person : persons) {
                int count = map.getOrDefault(person, 0) + 1;
                map.put(person, count);
                pq.offer(new int[]{count, index, person});
                who[index++] = pq.peek()[2];
            }
            this.times = times;
        }

        public int q(int t) {
            // 需要查找 t 在 times 中的位置，找到小于等于 t 的值 [5, 10, 15] (0) (6,1) (20,2)
            int n = times.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = (r - l + 1 >> 1) + l;
                int midVal = times[mid];
                if (midVal <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return who[l];
        }
    }

    /**
     * 1818. 绝对差值和
     * 替换 nums1 中的一个元素之后让绝对差之和变小，肯定是调整绝对值最大的那一个吧，
     * 用最接近 nums2 的值来替换
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int base = (int) 1e9 + 7;
        int n = nums1.length;
        int[] delta = new int[n];
        int sum = 0;
        int[] sort = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            sort[i] = nums1[i];
            delta[i] = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + delta[i]) % base;
        }
        Arrays.sort(sort);
        // 改变的差值的最大值
        int diff = 0;
        for (int i = 0; i < delta.length; i++) {
            // 在 nums1 中找到与 nums2[i] 最相近的两个数（边界情况下是一个数）（比较之后再返回一个最接近的）
            int near = findNear(sort, nums2[i]);
            diff = Math.max((delta[i] - Math.abs(sort[near] - nums2[i])), diff);
        }
        // sum - diff 有可能是负值，因为 sum 可能比 diff 大一轮
        return (sum - diff + base) % base;
    }

    private int findNear(int[] sort, int target) {
        int n = sort.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = sort[mid];
            // 最后求得的结果是第一个大于等于 target 的值
            if (midVal >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == 0) {
            return 0;
        }
        int diff1 = Math.abs(sort[l] - target);
        int diff2 = Math.abs(sort[l - 1] - target);
        // 跟前一个元素更接近 or 跟后一个元素更接近
        return diff1 > diff2 ? l - 1 : l;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l + 1 >> 1);
            int midVal = nums[mid];
            if (midVal <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // 找到的结果是 小于等于 target 的第一个数
        if (nums[l] != target) {
            return 0;
        }
        int count = 0;
        while (l > 0 && nums[l] == target) {
            count++;
            l--;
        }
        return count;
    }

    /**
     * 1894. 找到需要补充粉笔的学生编号
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        // 求前缀和
        int n = chalk.length;
        long[] sum = new long[n];
        sum[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }
        // 前缀和是单调递增的，我们的目标是找到第一个严格大于 k 的 index
        k %= sum[n - 1];
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l >> 1);
            long midVal = sum[mid];
            if (midVal >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 找到的是第一个大于等于 k 的 index
        return sum[l] == k ? l + 1 : l;
    }

    /**
     * 1414. 和为 K 的最少斐波那契数字数目
     * 既然求使用到的最少的数字，那么数字肯定就是越大越好咯，
     * 每一次都取可能的最大值，也就是取斐波那契数列中小于等于目标值的数
     *
     * @param k
     * @return
     */
    public int findMinFibonacciNumbers(int k) {
        // 先求斐波那契数列
        List<Integer> list = new ArrayList<>();
        int a = 1, b = 1;
        list.add(a);
        list.add(b);
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
            list.add(b);
        }

        // 在数组中找到最后一个小于等于 k 的值
        int count = 0;
        while (k > 0) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + (r - l + 1 >> 1);
                int midVal = list.get(mid);
                if (midVal == 0) {
                    r = mid;
                    continue;
                }
                if (midVal <= k) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            // 求的是最后一个小于等于 k 的值
            k -= list.get(l);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.findMinFibonacciNumbers(7);
    }

}
