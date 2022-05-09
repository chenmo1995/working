package com.leetcode.newyear.march;

/**
 * @author fdn
 * @since 2022-03-15 16:36
 */
public class Test0315 {

    /**
     * 2044. 统计按位或能得到最大值的子集数目
     * <p>
     * 显然所有数全部按位或得到的值最大
     *
     * @param nums
     * @return
     */
    public int countMaxOrSubsets(int[] nums) {
        // nums 最多只有 16 个，用 s 的低 16 位来表示 nums[i] 是否被选择
        int n = nums.length;
        int max = 0, cnt = 0;
        int status = 1 << n;// 第 17 位为 1，下面循环的时候会减一，让 s 的低 16 位全部为 1，来循环
        for (int i = 0; i < status; i++) {
            int ORSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    ORSum |= nums[j];
                }
            }
            if (ORSum == max) {
                cnt++;
            } else if (ORSum > max) {
                // 发现更大的按位或的值，重新计数，更新最大值
                max = ORSum;
                cnt = 1;
            }
        }
        return cnt;
    }
}
