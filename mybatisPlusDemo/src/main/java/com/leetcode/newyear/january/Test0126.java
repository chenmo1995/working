package com.leetcode.newyear.january;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fdn
 * @since 2022-01-26 09:49
 */
public class Test0126 {
    /**
     * 2013. 检测正方形
     * <p>
     * 用什么数据结构来存储这些数组呢
     * 有没有必要记录最小最大的 x，y 的值呢
     * <p>
     * 用哈希表来存储，{x, {y, count(x,y)}}，存储 (x, y)，并且记录 (x, y) 出现的次数
     */
    class DetectSquares {
        Map<Integer, Map<Integer, Integer>> pointMap;

        public DetectSquares() {
            pointMap = new HashMap<>();

        }

        public void add(int[] point) {
            int x = point[0], y = point[1];
            // 获取内部的 hashMap
            Map<Integer, Integer> countMap = pointMap.getOrDefault(x, new HashMap<>(16));
            // 操作内部 hashMap
            countMap.put(y, countMap.getOrDefault(y, 0) + 1);
            // 更新外部的 hashMap
            pointMap.put(x, countMap);
        }

        public int count(int[] point) {
            int result = 0;
            int x = point[0], y = point[1];
            // 找到与给定点相同 x坐标的点的集合，遍历他们，拿到 y坐标，分别找
            Map<Integer, Integer> countMap = pointMap.getOrDefault(x, new HashMap<>(16));
            for (Integer ay : countMap.keySet()) {
                // 可能存在的正方形的边长
                int len = ay - y;
                if (len == 0) {
                    continue;
                }
                // 找另外一条边上的两个点
                int[] leftOrRight = new int[]{x - len, x + len};
                for (int lor : leftOrRight) {
                    Map<Integer, Integer> map = pointMap.getOrDefault(lor, new HashMap<>(16));
                    Integer a = map.getOrDefault(ay, 0);
                    Integer b = map.getOrDefault(y, 0);
                    Integer c = countMap.getOrDefault(ay, 0);
                    result += a * b * c;
                }
            }
            return result;
        }
    }
}
