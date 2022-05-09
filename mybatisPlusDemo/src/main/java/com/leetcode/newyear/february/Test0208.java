package com.leetcode.newyear.february;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-02-08 10:25
 */
public class Test0208 {

    /**
     * 1001. 网格照明   哈希表 + 线映射
     * <p>
     * 这套题个人觉得很经典，就是网格上面的模拟，对网格 8 个方向的处理，对行、列、对角线的处理都可以借鉴
     *
     * @param n
     * @param lamps
     * @param queries
     * @return
     */
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        // 用四个 Map 来记录行、列、对角线的状态
        // <行，状态>
        Map<Integer, Integer> row = new HashMap<>(16);
        Map<Integer, Integer> col = new HashMap<>(16);
        // 用 x-y 来表示左对角线（/），x+y 来表示右对角线（\）
        Map<Integer, Integer> left = new HashMap<>(16);
        Map<Integer, Integer> right = new HashMap<>(16);

        // 遍历灯的位置，处理行、列、对角线的状态。
        // 同时需要记录灯的位置，防止重复处理（因为重复处理会导致我们的 Map 中的 value 多做一次加一操作，
        // 在执行灭灯操作的时候会导致 value 无法归零；当然这也是可以在点亮操作的时候处理的，不做加一操作，做赋值操作就好了）
        // 这里有一个技巧【二维转一维】：index = x*n + y；可以唯一确定二维平面的一个点；注意 n 很大，会溢出
        Set<Long> set = new HashSet<>();
        for (int[] lamp : lamps) {
            // x 表示行，y 表示列
            int x = lamp[0], y = lamp[1];
            // a 表示右对角线，b 表示左对角线
            int a = x + y, b = x - y;
            // 灯位置重复，则不作处理
            if (set.contains(x * (long) n + y)) {
                continue;
            }
            // 处理行、列、对角线的状态
            increment(row, x);
            increment(col, y);
            increment(left, b);
            increment(right, a);
            // 记录灯位置
            set.add(x * (long) n + y);
        }
        // 开始查询并灭灯
        int length = queries.length;
        int[] result = new int[length];
        // 有 9 个点哈，不要忘了自己
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < length; i++) {
            int[] query = queries[i];
            int x = query[0], y = query[1];
            int a = x + y, b = x - y;
            // 查询点的行、列、对角线的 value 大于 0 代表是该点是电量状态
            // 不能用大于 0 来判断，因为有些 key，根本就不在 map 中
            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(b) || right.containsKey(a)) {
                result[i] = 1;
            }
            // 灭灯
            for (int[] dir : dirs) {
                // 找到其他八个点的行列对角线
                int nx = x + dir[0], ny = y + dir[1];
                int na = nx + ny, nb = nx - ny;
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    // 排除掉超出边界的值
                    continue;
                }
                // 灭灯
                if (set.contains(nx * (long) n + ny)) {
                    set.remove(nx * (long) n + ny);
                    decrement(row, nx);
                    decrement(col, ny);
                    decrement(left, nb);
                    decrement(right, na);
                }
                // 检查点无灯源就不用灭灯了
            }
        }
        return result;
    }


    /**
     * 将行、列、对角线点亮
     *
     * @param map
     * @param key
     */
    private void increment(Map<Integer, Integer> map, int key) {
        // 有的行会点亮多次，value 会大于 1 ，这正是我们想要的；点亮多次，熄灭一次，还是点亮状态
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    /**
     * 灭掉该点的灯
     *
     * @param map
     * @param key
     */
    private void decrement(Map<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }
}
