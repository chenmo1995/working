package com.leetcode.newyear.interview21;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 面试高频：Top K 问题的多种解法：冒泡排序 & 快速排序 & 优先队列 ...
 *
 * @author fdn
 * @since 2022-01-22 13:43
 */
public class TopK {

    /*
    构造一个优先队列，里面存放 k 个数据（本题是要求第 k 大的数据），所以优先队列按照小顶堆来维护，
    这样堆顶的元素就是第 k 大的数据
    然后依次往后遍历数组，nums[i] < pop，就可以把元素丢弃了
    nums[i] > pop，就弹出 pop，并将 nums[i] 入队
     */
    class KthLargest {
        PriorityQueue<Integer> pq;
        int K;

        public KthLargest(int k, int[] nums) {
            K = k;
            pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));
            int n = nums.length;
            for (int i = 0; i < n && i < k; i++) {
                pq.offer(nums[i]);
            }
            int index = k;
            while (index < n) {
                if (pq.peek() >= nums[index]) {
                    index++;
                } else {
                    pq.poll();
                    pq.offer(nums[index]);
                    index++;
                }
            }
        }

        public int add(int val) {
            if (pq.size() < K) {
                pq.offer(val);
            } else if (pq.peek() < val) {
                pq.poll();
                pq.offer(val);
            }
            return pq.peek();
        }
    }

}
