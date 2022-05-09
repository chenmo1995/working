package com.leetcode.backtracing;

import java.util.*;

/**
 * 332. 重新安排行程
 *
 * @author fdn
 * @since 2022-04-25 14:28
 */
public class ReconstructItinerary {


    List<String> result = new ArrayList<>();
    Map<String, Map<String, Integer>> map;
    int n;

    public List<String> findItinerary(List<List<String>> tickets) {
        // 先构造 Map<起始地，Map<目的地，次数>
        map = new HashMap<>(16);
        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new TreeMap<>());
            Map<String, Integer> temp = map.get(ticket.get(0));
            temp.put(ticket.get(1), temp.getOrDefault(ticket.get(1), 0) + 1);
        }
        result.add("JFK");
        n = tickets.size();
        backTracing();
        return result;
    }

    private boolean backTracing() {
        // 终止条件
        if (result.size() == n + 1) {
            return true;
        }
        String from = result.get(result.size() - 1);
        if (map.containsKey(from)) {
            for (Map.Entry<String, Integer> entry : map.get(from).entrySet()) {
                // count 是出发地 from 到目的地的 to = entry.key 的票的张数
                int count = entry.getValue();
                if (count > 0) {
                    // 可以尝试
                    result.add(entry.getKey());
                    entry.setValue(count - 1);
                    // 继续去找下一趟行程
                    if (backTracing()) {
                        // 这里的目的是：当找到一条路径符合条件时，快速返回结果，后续所有的撤销操作都不需要做
                        return true;
                    }
                    entry.setValue(count);
                    result.remove(result.size() - 1);
                }
            }
        }
        return false;
    }
}
