package com.leetcode.newyear.january;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 1036. 逃离大迷宫
 *
 * @author fdn
 * @since 2022-01-11 10:57
 */
public class Test0111 {

    // 在包围圈中
    static final int BLOCKED = -1;
    // 不在包围圈中
    static final int VALID = 0;
    // 无论在不在包围圈中，但在 n(n-1)/2 步搜索的过程中经过了 target
    static final int FOUND = 1;

    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int BOUNDARY = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }

        Set<Pair> hashBlocked = new HashSet<Pair>();
        for (int[] pos : blocked) {
            hashBlocked.add(new Pair(pos[0], pos[1]));
        }

        int result = check(blocked, source, target, hashBlocked);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
            result = check(blocked, target, source, hashBlocked);
            return result != BLOCKED;
        }
    }

    public int check(int[][] blocked, int[] start, int[] finish, Set<Pair> hashBlocked) {
        int sx = start[0], sy = start[1];
        int fx = finish[0], fy = finish[1];
        int countdown = blocked.length * (blocked.length - 1) / 2;
        Pair startPair = new Pair(sx, sy);
        // 记录下一层需要前进的位置和本层还没走完的位置
        Queue<Pair> queue = new ArrayDeque<Pair>();
        queue.offer(startPair);
        // 记录已经走过的位置，走过的位置不能再走，否则成环了
        Set<Pair> visited = new HashSet<Pair>();
        visited.add(startPair);
        // 广度优先遍历，每个节点都有上下左右四个位置可以前进
        while (!queue.isEmpty() && countdown > 0) {
            Pair pair = queue.poll();
            int x = pair.x, y = pair.y;
            // 注意这里区别与二叉树的层序遍历，二叉树在这里是求出队列的长度，然后把这一层遍历完。是为了一层一层遍历（不记录长度，就无法一层层遍历）
            // 这里不需要一层一层，只要队列不为空，就可以继续处理队列中的节点
            // 四个位置
            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                Pair newPair = new Pair(nx, ny);
                // 四个可选位置必须在大的网格内，且可选位置不是障碍位置，且没有走过
                if (nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY && !hashBlocked.contains(newPair) && !visited.contains(newPair)) {
                    if (nx == fx && ny == fy) {
                        // 便利的时候走到了 source 位置，直接返回可以达到目的地
                        return FOUND;
                    }
                    --countdown;
                    queue.offer(newPair);
                    visited.add(newPair);
                }
            }
        }
        // 走出循环要么是超过了 n(n-1) 步，要么是可选位置走完了
        // 如果可选位置走完了，说明无路可走，被堵住了；如果超过了 n(n-1) 步，已经超出了理论最大障碍区间，说明没有被堵住
        if (countdown > 0) {
            return BLOCKED;
        }
        return VALID;
    }


    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return (int) ((long) x << 20 | y);
        }

        /**
         * Set::contains 方法中使用了 key 的 equals 方法，使用的是 Object::equals 方法，直接用 == 判断内存地址的
         * 我们希望 x,y 相等，就认为对象相等，所以需要重写 equals 方法，自然也需要重写一下 hashcode ，根据 x,y 来写（因为对象相等，hashcode 必须相等）
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair pair2 = (Pair) obj;
                return x == pair2.x && y == pair2.y;
            }
            return false;
        }

    }
}
