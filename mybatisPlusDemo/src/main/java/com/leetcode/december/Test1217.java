package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-17 15:04
 */
public class Test1217 {

    /**
     * 347. 前 K 个高频元素
     * <p>
     * 优先队列好像可以，按照元素的频次取最大值。但是复杂度好像比较高，试一下
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] max = new int[k];
        // Map<元素,元素的频次>，以频次顺序排序
        Map<Integer, Integer> map = new HashMap<>(16);
        // 优先队列中值是map的key，也就是元素本身
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) ->
                !map.getOrDefault(i1, 0).equals(map.getOrDefault(i2, 0))
                        ? map.getOrDefault(i1, 0) - map.getOrDefault(i2, 0)
                        : i2 - i1);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() == k) {
                if (entry.getValue() > map.get(pq.peek())) {
                    pq.poll();
                    pq.offer(entry.getKey());
                } else {
                    //直接丢弃
                }
            } else {
                pq.offer(entry.getKey());
            }
        }
        for (int i = 0; i < k; i++) {
            // size在变化
            max[i] = pq.poll();
        }
        return max;
    }

    public static void main(String[] args) {
        Test1217 test1217 = new Test1217();
//        List<List<Integer>> combine = test1217.combine(4, 2);
//        System.out.println(combine);
    }
}



















