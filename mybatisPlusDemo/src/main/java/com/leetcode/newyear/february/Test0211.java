package com.leetcode.newyear.february;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-02-11 00:06
 */
public class Test0211 {
    /**
     * 1984. 学生分数的最小差值
     *
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        for (int l = 0, r = k - 1; r < n; l++, r++) {
            result = Math.min(result, nums[r] - nums[l]);
        }
        return result;
    }

    /**
     * 1985. 找出数组中的第 K 大整数
     * 构造一个小顶堆，容量为 k，那么堆顶就是第 k 大的数
     *
     * @param nums
     * @param k
     * @return
     */
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a.length() > b.length()) {
                        return 1;
                    } else if (a.length() < b.length()) {
                        return -1;
                    } else {
                        return a.compareTo(b);
                    }
                }
        );
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        // 堆里面有 k 个数了
        for (int i = k; i < n; i++) {
            String peek = pq.peek();
            if (nums[i].length() > peek.length() || (nums[i].length() == peek.length() && nums[i].compareTo(peek) > 0)) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4", "5", "100", "6", "108", "7", "9"};
        Arrays.sort(arr, (a, b) -> {
                    if (a.length() > b.length()) {
                        return 1;
                    } else if (a.length() < b.length()) {
                        return -1;
                    } else {
                        return a.compareTo(b);
                    }
                }
        );
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println("102".compareTo("111"));
    }
}
