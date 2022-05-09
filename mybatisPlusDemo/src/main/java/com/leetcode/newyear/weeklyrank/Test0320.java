package com.leetcode.newyear.weeklyrank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2022-03-20 10:19
 */
public class Test0320 {
    /**
     * 6027. 统计数组中峰和谷的数量
     *
     * @param nums
     * @return
     */
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (i > 0 && nums[i - 1] == cur) {
                continue;
            }
            int index = i;
            while (index - 1 >= 0 && nums[index - 1] == cur) {
                index--;
            }
            int left = 0;
            if (index == 0) {
                continue;
            } else {
                left = nums[index - 1];
            }
            index = i;
            while (index + 1 < n && nums[index + 1] == cur) {
                index++;
            }
            int right = 0;
            if (index == n - 1) {
                continue;
            } else {
                right = nums[index + 1];
            }
            if ((left < cur && right < cur) || (left > cur && right > cur)) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 6028. 统计道路上的碰撞次数
     * <p>
     * 记录最左边的 R 和 S，和最右边的 L 和 S
     * 当前位置为 R，只要右边有 L 或者 S，一定会撞
     * 当前位置为 L，只要左边有 R 或者 S，一定会撞
     * 当前位置为 S，只要左边有 R，或者右边有 L，一定会撞
     *
     * @param directions
     * @return
     */
    public int countCollisions(String directions) {
        char[] chars = directions.toCharArray();
        int n = chars.length;
        int LR = n - 1, LS = n - 1;
        int RL = 0, RS = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'L') {
                if (i > RL) {
                    RL = i;
                }
            } else if (chars[i] == 'R') {
                if (i < LR) {
                    LR = i;
                }
            } else {
                if (i < LS) {
                    LS = i;
                }
                if (i > RS) {
                    RS = i;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'L') {
                // 只要左边有 R 或者 S
                if (LR < i || LS < i) {
                    cnt++;
                }
            } else if (chars[i] == 'R') {
                // 只要右边有 L 或者 S
                if (RL > i || RS > i) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * 6029. 射箭比赛中的最大得分
     * 1、得不到分的地方，一箭都不会射
     *
     * @param numArrows
     * @param aliceArrows
     * @return
     */
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int n = aliceArrows.length;
        // 每一位表示 bob 在该区域的得分情况，二进制位上为 1 就是得分了
        int status = (1 << 12) - 1;
        // 需要选取得分最大的
        int maxScore = 0;
        int[] result = new int[12];
        for (int i = status; i > 0; i--) {
            int use = 0, score = 0;
            // 打成这种得分分布需要的 ➹ 的数量
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    use += aliceArrows[j] + 1;
                    score += j;
                }
            }
            if (use <= numArrows && score > maxScore) {
                // 更新最大得分
                maxScore = score;
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 1) {
                        result[j] = aliceArrows[j] + 1;
                    } else {
                        result[j] = 0;
                    }
                }
                // 多余的 ➹，任意放在哪里都行
                result[0] += numArrows - use;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Test0320 test0320 = new Test0320();
        test0320.maximumBobPoints(9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0});
    }
}
