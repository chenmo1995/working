package com.leetcode.december;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-12-19 00:25
 */
public class Test1219 {



    /**
     * 997. 找到小镇的法官
     * 如果存在法官，那么所有人的信任对都有他，同时，他自己又不能产生信任对
     * <p>
     * 每个人都信任法官，意味着除了法官之外每个人都有信任对，所以只要把被信任的最多的那个人找到，校验它 == n-1
     * 同时他自己还没有信任对，就找到了
     *
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust) {
        if (trust.length == 0) {
            return n == 1 ? 1 : -1;
        }
        // 人编号，被信任的次数
        Map<Integer, Integer> map = new HashMap<>(16);
        // 信任过别人的人
        Set<Integer> others = new HashSet<>();

        for (int[] ints : trust) {
            // 记录每个人被信任的次数
            map.put(ints[1], map.getOrDefault(ints[1], 0) + 1);
            others.add(ints[0]);
        }
        // value最大的key
        Integer judge = map.keySet().stream().sorted((a, b) -> map.getOrDefault(b, 0) - map.getOrDefault(a, 0)).collect(Collectors.toList()).get(0);
        return (map.getOrDefault(judge, 0) == n - 1) && !others.contains(judge) ? judge : -1;
    }

    /**
     * 出入度。投了人，出度加1，被人投，入度加1
     * 找出出度为0，入度为n-1的，即为答案
     *
     * @param n
     * @param trust
     * @return
     */
    public int findJudge2(int n, int[][] trust) {
        int[] in = new int[n + 1], out = new int[n + 1];
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            in[b]++;
            out[a]++;
        }
        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) return i;
        }
        return -1;
    }

}
