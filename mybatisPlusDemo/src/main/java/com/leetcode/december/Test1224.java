package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-24 09:46
 */
public class Test1224 {

    /**
     * 1705. 吃苹果的最大数目
     * 贪心+优先队列
     * 很容易想到，每天吃最临近过期的苹果可以保证吃到的苹果最多
     *
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        /* 维护一个优先队列，队列中存放一个二元数组 [过期时间，当天长出来的苹果数]
           按照过期时间顺序排列，堆顶就是最早过期时间
           如何维护这个队列呢，怎么添加，怎么弹出呢？
           添加的时候，每天插入，插入 [当前时间 + days[i] ，apples[i]]
                      然后每天吃， [过期时间，apples[i]-1]]
           如果过期了，弹出堆顶元素，堆顶不能再吃了。如果吃完了，也弹出堆顶
        */
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // time 表示当前时间， result 为吃到的最多数量
        int n = apples.length, time = 0, result = 0;
        // 时间超过了 n ，且苹果吃完了（也即队列为空），就表示模拟结束了
        while (time < n || !pq.isEmpty()) {
            // 入队，只有苹果数量大于 0 才需要入队;这里的 time 就是循环里面的 i 了
            if (time < n && apples[time] > 0) {
                pq.offer(new int[]{time + days[time], apples[time]});
            }
            // 先出队，避免数量为 0 的苹果数组在堆顶 (其实不会，因为数量为 0 一开始就无法入队，
            // 维护的过程中如果数量减少到 0 ，也不会入队);过期时间就是今天，不能吃
            while (!pq.isEmpty() && pq.peek()[0] <= time) {
                pq.poll();
            }
            // 吃苹果，吃的时候注意，弹出堆顶元素，吃完之后重新入队
            if (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int expire = cur[0];
                int num = cur[1];
                result++;
                num--;
                // 剩余苹果数量大于 0 才入队
                if (num > 0) {
                    pq.offer(new int[]{expire, num});
                }
            }
            // 这一天结束了
            time++;
        }
        return result;
    }

    /**
     * 47. 全排列 II
     * <p>
     * 肯定用不上 startIndex ，因为元素可以乱序取
     * 元素不能重复取，可以用 used 数组来过滤
     * 相同元素同一层也不能取
     *
     * @param nums
     * @return
     */
    List<List<Integer>> result;
    List<Integer> path;
    int[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        used = new int[nums.length];
        // 先排序，方便后面判断是否使用过相等但不是同一个元素
        Arrays.sort(nums);
        backTracing(nums, used);
        return result;
    }

    private void backTracing(int[] nums, int[] used) {
        // 收集
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 终止条件写在了收集结果里面

        // 单次遍历
        for (int i = 0; i < nums.length; i++) {
            // 两种情况不能取，一是该元素已经在这一【树枝】使用过，
            // 一是前一个元素与自己相同，且前一个元素在这一【数层】使用过
            if ((i > 0 && nums[i] == nums[i - 1]) && used[i - 1] == 0 || used[i] == 1) {
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backTracing(nums, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }
}
















