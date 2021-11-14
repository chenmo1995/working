package com.leetcode.september;

/**
 * 1894. 找到需要补充粉笔的学生编号
 * <p>
 * 前缀和+二分法
 *
 * @author fdn
 * @since 2021-09-11 15:43
 */
public class Test0911 {
    public static void main(String[] args) {
        chalkReplacer(new int[]{3, 4, 1, 2}, 25);
    }

    public static int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] sum = new long[n];
        sum[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }
        int target = (int) (k % sum[n - 1]);
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int midVal = l + r >> 1;
            if (target >= sum[midVal]) {
                l = midVal + 1;
            } else {
                r = midVal;
            }
        }
        return l;
    }

    /**
     * 600. 不含连续1的非负整数
     * dp
     *
     *
     * 太难把细节说清楚了，结合二叉树来看
     * @param n
     * @return
     */
    public int findIntegers(int n) {
        /**
         *
         * dp[t] 表示高度为t-1(也就是t-1位,也就是最高位是第t-1位)、根节点为0的满二叉树中，不包含连续1的从根节点到叶节点的路径数量
         * dp[2] = 2,dp[3] = 3;      可以令dp[1] = 1,dp[0] = 1;     求转移方式的时候令t>=2
         *
         */
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            /**
             * dp[i]求值  根节点为0，高度是i-1;
             * dp[i] = i-1位是0,和i-1位是1两种情况下符合条件的数量之和
             * i-1位是0,则这种情况下满足条件的数量是dp[i-1]
             * i-1位是1,则i-2位必然不是1,必然等于0,,所以这种情况下满足条件的数量是dp[i-2]
             * 所以 dp[i] = dp[i-1] + dp[i-2]
             */
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                //第一次判断的是 n的第30(也就是第i+1位)位是否是0,不是0,则进入,也就是先找到n的最高位
                res += dp[i + 1];//第i+1位为0,后面的i位中满足条件的路径数量(个人理解是为了满足该路径的数比n小)
                /**
                 * n的值肯定是  1001 0010
                 * 此时把n拆开看,拆成 0*** **** 小于1000 0000 的部分
                 *                和1001 ****  大于1000 0000 ，小于1001 0000的部分
                 *                再拆成1001 001* 大于1001 0000，小于1001 001*的部分
                 *                可以一直拆
                 *                直到，连续两位为1，后面就不用拆了，更大的数不存在满足条件的情况了
                 *                所以上面一步才会循环加上dp[i+1]
                 */
                if (pre == 1) {
                    //从最高位往下数,一旦出现连续两位为1(假设是p和q两位),后面的位的值无论是1还是0,
                    //这个数满足条件的路径数量就是【p位值为1,q位值为0,后面所有位的值都是1】的情况下满足条件的路径数量,
                    //此时就把问题转化成了满二叉树的情况
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }
}
