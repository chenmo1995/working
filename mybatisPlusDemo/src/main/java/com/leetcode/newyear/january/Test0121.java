package com.leetcode.newyear.january;

import java.util.*;

/**
 * @author fdn
 * @since 2022-01-21 16:18
 */
public class Test0121 {

    /**
     * 1345. 跳跃游戏 IV
     * 想到个笨方法，从最后一个元素开始，依次将可能到达的元素（假设是 A ）需要的步骤标记为 1，
     * 然后再将可以到达 A 的元素（假设是 B）标记为 2，以此类推，每标记一个就删掉一个元素，直到元素都被删了
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        int INF = 0x3f3f3f3f;
        int n = arr.length;

        Map<Integer, List<Integer>> indexMap = new HashMap<>(16);
        // 用 map 记录每个元素的位置
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> integers = indexMap.getOrDefault(arr[i], new ArrayList<>());
            integers.add(i);
            indexMap.put(arr[i], integers);
        }
        // 记录到达某个位置的最小步数（初始化为 INF,代表没遍历过）
        // dist[i] 表示到达位置 i 需要的最小步数
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        // Deque 中存放元素位置的索引，遍历的时候往里面加入可以到达的三种情况的元素
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        dist[0] = 0;
        while (!deque.isEmpty()) {
            int first = deque.pollFirst();
            int step = dist[first];
            // 如果是最后一位，找到答案了，直接返回结果
            if (first == n - 1) {
                return step;
            }
            // 加入后一位
            if (first + 1 < n && dist[first + 1] == INF) {
                deque.offerLast(first + 1);
                // 到达后一位的位置加 1
                dist[first + 1] = step + 1;
            }
            // 加入前一位
            if (first - 1 >= 0 && dist[first - 1] == INF) {
                deque.offerLast(first - 1);
                dist[first - 1] = step + 1;
            }
            // 加入等值的
            List<Integer> list = indexMap.getOrDefault(arr[first], new ArrayList<>());
            for (Integer integer : list) {
                if (dist[integer] == INF) {
                    deque.offerLast(integer);
                    dist[integer] = step + 1;
                }
            }
            // 等值的元素的下标也会加入 deque，为了不重复便利，要在 map 中将这个 key 移除掉，
            // 让下一次获取 list 的时候，获取空 list
            indexMap.remove(arr[first]);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(0x3f3f3f3f);
    }
}
