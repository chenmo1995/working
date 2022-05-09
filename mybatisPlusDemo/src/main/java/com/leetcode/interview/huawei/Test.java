package com.leetcode.interview.huawei;

/**
 * @author fdn
 * @since 2022-04-19 19:32
 */
public class Test {
    public static void main(String[] args) {

        int test = test(new int[]{0, 9, 0, 1, 1, 1, 1, 1,1});
        System.out.println(test);
    }

    /**
     * 给你一个整数类型的数组，要求你每次取一个数字，但你取完这个数字后，
     * 这个数字左边和右边的数字不允许去取，输出你能取到的数字累加起来总数最大的值。
     * <p>
     * 正整数，那就只有两种情况了
     * 不对
     * [0, 5, 0, 1, 9]
     * [0, 9, 0, 1,1,1,1,1]
     * [0, 5, 0, 8, 9, 8] 也不能直接取最大数
     * 也不能排序
     * <p>
     * <p>
     * dp[i][j]，j 取 0或1，代表 index = i 的数取不取
     * dp[0][0] = 0                             dp[0][1] = nums[0]  2
     * dp[1][0] = nums[0]      2                dp[1][1] = nums[1]  5
     * dp[2][0] = max(nums[0],nums[1])   5       dp[2][1] = nums[0] + nums[2]    5
     * 5    9
     * <p>
     * dp[i + 1][0] = max(dp[i][0],dp[i][1])
     * dp[i + 1][1] = dp[i][0] + nums[i + 1]
     * <p>
     * 如：
     * 输入：[2,5,3,4]
     * 输出：9
     * 解释：先拿5，2和3不允许拿，然后拿4，累加起来最大的总数为5 + 4 = 9。
     */
    static int test(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 0; i < n - 1; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1]);
            dp[i + 1][1] = dp[i][0] + nums[i + 1];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
