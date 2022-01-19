package com.leetcode.december;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-12-19 00:25
 */
public class Test1219 {

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    // 记录前面一个数是在同一数层还是在同一树枝
    int[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new int[candidates.length];
        // 必须排序，不然后面用used去重实现不了
        Arrays.sort(candidates);
        backTracing(0, candidates, target, 0, used);
        return result;
    }

    private void backTracing(int sum, int[] candidates, int target, int startIndex, int[] used) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // && sum + candidates[i] <= target; 这个剪枝一般是在数组从小到大排序了之后才可以使用的
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0) {
                continue;
            }
            // used[i - 1] == 1，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == 0，说明同一树层candidates[i - 1]使用过
            path.add(candidates[i]);
            sum += candidates[i];
            used[i] = 1;
            // 带着1进入递归下一层
            backTracing(sum, candidates, target, i + 1, used);
            used[i] = 0;
            // 带着0进入for循环的下一层
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }

    }

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
