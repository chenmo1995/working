package com.leetcode.newyear.january;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fdn
 * @since 2022-01-04 19:21
 */
public class TestDP {

    /**
     * 509. 斐波那契数
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        // dp[i] 表示 F(i)
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 70. 爬楼梯
     * 第 k 层可以由第 k-1 层直接爬上来和第 k-2 层直接爬上来
     * dp[i]的定义：爬到第i层楼梯，有dp[i]中方法。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        // dp[i]的定义：爬到第i层楼梯，有dp[i]中方法。
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 到底第 k 层，需要花费的力气是 min(dp[k-1],dp[k-2])+nums[k]
     * <p>
     * dp[i] 表示到达第 i 层使用的最小的力气
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    /**
     * 62. 不同路径
     * 这题有图，动归递推起来比较容易看出来
     * 从左上角往右下角推
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 每一格都有正上方和正左方两种方式移动过来
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        int p;
        for (p = 0; p < m; p++) {
            if (obstacleGrid[p][0] == 1) {
                break;
            } else {
                dp[p][0] = 1;
            }
        }
        for (int i = p; i < m; i++) {
            dp[p][0] = 0;
        }
        int q;
        for (q = 0; q < n; q++) {
            if (obstacleGrid[0][q] == 1) {
                break;
            } else {
                dp[0][q] = 1;
            }
        }
        for (int i = q; i < n; i++) {
            dp[0][q] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];

                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 343. 整数拆分
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // 定义 dp[i] 为正整数 i 可以被拆分之后的最大乘积
        // dp[i] 递推公式呢？dp[i] 可以怎么得到呢
        // i 可以拆成 j 和 i-j dp[i] 就可以表示成 j*(i-j) 的最大值；但是 (i-j) 还可以继续拆，
        // 我们用 dp[i-j] 就可以表示继续拆分的最大乘积了。
        // 随着 j 的遍历，dp[i]会有不同的取值，取出最大的
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     * 这题的地推公式不好求，但是通过举例，慢慢可以发现规律。遍历根节点的数值来求种数
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // dp[i] 表示 数为 i 的二叉搜索树的种数
        int[] dp = new int[n + 1];
        // 为了方便后面的递推公式
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 01背包问题模板
     * 求可以背的物品的最大价值
     */
    public void zeroOnePackage() {
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        int maxVolume = 4;
        // 构造 dp 数组；dp[i] 表示容量为 i 的背包可以装下的物品最大价值
        int[] dp = new int[maxVolume + 1];
        // 初始化 dp 数组;其他项初始化为 0，避免被覆盖
        dp[0] = 0;
        // 遍历顺序，先遍历物品，再遍历背包。因为我们是把二维数组压缩成了一行，每个物品对应一行
        for (int i = 0; i < weight.length; i++) {
            // 倒序遍历背包。因为 dp 数组是重复使用的，从前往后遍历会导致改行物品被重复添加
            // 其实再遍历第二个物品的时候，dp 数组中元素已经不是初始时的 0 了；
            // 而是上一层（回忆下二维数组）的 dp 数组
            for (int j = maxVolume; j >= 0; j--) {
                if (j - weight[i] < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
                System.out.print(dp[j] + "\t");
            }
            System.out.print("\n");
        }
    }

    /**
     * 416. 分割等和子集
     * 用回溯肯定是可以实现的，但是时间复杂度非常高，每个元素都有选与不选的两种状态，整体时间复杂度达到了 2^n
     * 使用动归的话，如何定义 dp 数组呢
     * 物品是 nums[i] 重量为 nums[i] 价值也为 nums[i] 背包容量呢，重量的和吗？(是的)
     * dp[i] 表示容量为 i 的背包所能装的物品的最大价值（好像也符合题意，这就转成背包了？）
     * dp 数组的长度就要定义成 sum/2 + 1（如果 sum 是奇数，直接返回 false 了）
     * 最后，如果 dp[sum/2+1] = sum/2 的话，就返回 true，否则返回 false
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int[] dp = new int[sum / 2 + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum / 2; j > 0; j--) {
                if (j - nums[i] < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
            }
        }
        return dp[sum / 2] == sum / 2;
    }

    /**
     * 1049. 最后一块石头的重量 II
     * 等一下，这好像也是把一个数组，平均分成两个和相等的子数组的问题，或者说，分成两个和最接近的子数组
     * i 是物品，stones[i] 是物品重量，stones[i] 也是物品价值。dp[i] 表示容量为 i 的背包能装物品的最大价值
     * sum/2 为偶数，dp 数组的长度就是 sum/2 + 1；sum/2 为奇数 长度也还是 sum/2 + 1；
     * 最后剩余的石头最小可能重量就是 (sum - dp[sum/2])-dp[sum/2];
     * <p>
     * 因为 dp[sum/2] 已经是尽量的在装了，一半总容量尽量装一半的东西，装满的话，就能均分，装不满，也已经是最好的结果了
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int[] dp = new int[sum / 2 + 1];
        dp[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = sum / 2; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        // 在计算target的时候，target = sum / 2 因为是向下取整，所以sum - dp[target] 一定是大于等于dp[target]的。
        return Math.abs((sum - dp[sum / 2]) - dp[sum / 2]);
    }

    /**
     * 494. 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        // leftSum - rightSum = target
        // leftSum + rightSum = sum
        // leftSum = (target + sum)/2
        // 问题转化成在 nums 找到和为 leftSum 的可行方案
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // leftSum 不为整数，则不存在
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        // leftSum 可能为负数
        int leftSum = Math.abs(sum + target) / 2;
        // dp[j] 表示前 i 个数，装满容量为 j 的背包的方案数
        // leftSum 就是背包的总容量，nums[i] 是重量
        int[] dp = new int[leftSum + 1];
        // 装满背包容量为 0 的方案数是 1 ，即什么都不装
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = leftSum; j >= nums[i]; j--) {
                // j < nums[i] 时：dp[j] = dp[j]
                // 因为 dp 数组是重复使用上一层的，所以已经有值了，无需再次赋值
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[leftSum];
    }

    /**
     * 474. 一和零
     * 其实很容易想到这是一个背包容量有两个维度的 01背包问题
     * 回忆下 01背包问题的描述。从 nums 个物品中取任意物品到容量为 V 的背包中，
     * 求解可以装的最大价值，物品的体积 weight ，价值为 value
     * <p>
     * 对于本题：从 strs(物品) 中取任意物品到容量为 (V,M) 的背包中
     * 求解可以装的最大数量，物品的体积 (01的个数)；价值是什么，其实价值就是物品个数
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 背包的容量就是 (m,n) 了
        // dp[i][j] 表示容量为 i和j(分别为装 0和1 的数量) 的背包能装下的最大数量的物品
        int[][] dp = new int[m + 1][n + 1];
        // dp[0][j] 有可能不为 0 ，物品的其中一维体积是 0 是存在的，所以 dp[0][j] 还是需要遍历求解
        dp[0][0] = 0;
        for (String str : strs) {
            // 求出当前物品的两个维度的体积
            int zeroCount = 0, oneCount = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            for (int i = m; i >= zeroCount; i--) {
                for (int j = n; j >= oneCount; j--) {
                    // 选择了当前物品的情况下，dp[i][j] 需要加 1，也就是价值需要增加 value[i]
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroCount][j - oneCount] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 518. 零钱兑换 II
     * 数量无限个，想到完全背包；背包容量就是 amount ，物品重量和价值都是 coins[i]
     * 求组合数，递推公式有一点变化了，dp[i] = 考虑物品 i 的组合数 + 不考虑物品 i 的组合数
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // dp[i] 表示背包容量为 i 的时候，选硬币凑成总数的组合数（总数即为 i ）
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {//5,2,1
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
            for (int e : dp) {
                System.out.print(e + "\t");
            }
            System.out.print("\n");
        }
        System.out.println();
        int[] dp2 = new int[amount + 1];
        dp2[0] = 1;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {//5,2,1
                if (i - coins[j] >= 0) {
                    dp2[i] = dp2[i] + dp2[i - coins[j]];
                }
            }
            for (int e : dp2) {
                System.out.print(e + "\t");
            }
            System.out.print("\n");
        }

        return dp[amount];
    }

    /**
     * 377. 组合总和 Ⅳ
     * 完全背包求排列数的问题
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        // dp[i] 表示从 nums 中选取元素，装满容量为 i 的背包的排列数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，
        // 举一个例子：计算dp[4]的时候，结果集只有 {1,3} 这样的集合，不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后面！
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 322. 零钱兑换
     * 这是完全背包问题求选择最少物品数量装满背包的问题，难点肯定是在递推公式
     * 求最小值，初始化的时候也要注意；
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i] 表示物品装满容量为 i 的背包所需物品最小个数
        int[] dp = new int[amount + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 如果 dp[j - coins[i]] == Integer.MAX_VALUE，就表示将背包装成容量为 j - coins[i] 是不可能的，
                // 所以 dp[j] 的这种情况也不存在了
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 279. 完全平方数
     * 这也是完全背包；从 1,4,9,16... 等物品中选取，恰好装满容量为 j 的背包，物品可以重复选
     * 不存在装不满的情况，因为有 1
     * 背包体积 1 <= n <= 10000;所以物品只需要考虑 1~100；
     * dp[j] = min(dp[j], dp[j-weight[i]]+1)
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // dp[i] 表示选取一定物品恰好装满容量为 i 的背包，需要的最小的物品的数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = i * i; j < n + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 139. 单词拆分
     * s 就是背包，单词就是物品，单词组成字符串 s 就是物品装满背包
     * 单词可以重复取，就是完全背包
     * 递推公式分析：dp[i] 可以被装满的必要条件是 dp[j] 为 1，且，[j, i]字符串在物品列表里面
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] 表示字符串长度为 i 的时候，物品能否装满背包
        // 可以装满 dp[i] == 1，否则 dp[i] == 0
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 其实这里不需要遍历物品，因为 dp[i] 的值跟物品的index无关（不像之前的很多题，递推公式都需要 dp[j-weight[i]]）
        // 因为要截取字符串，所以一层循环遍历 左边界，一层遍历右边界
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                //[j, i]
                String substring = s.substring(j, i);
                if (dp[j] == 1 && words.contains(substring)) {
                    dp[i] = 1;
                }
            }
        }
        return dp[n] == 1;
    }

    /**
     * 198. 打家劫舍
     * dp[i] 有偷和不偷 i 两种
     * 偷 dp[i-2] + nums[i]
     * 不偷 dp[i-1]
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        // dp[i] 表示有 i 户，能偷的最多的钱
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[n];
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        // dp[i] 表示在第 i 天卖出能获取的最大利润
        int n = prices.length;
        int max = 0;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(0, dp[i - 1] + prices[i] - prices[i - 1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 121. 买卖股票的最佳时机   贪心
     * 取左边最小值，取右边最大值，相减即可
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            result = Math.max(result, prices[i] - min);
        }
        return result;
    }

    /**
     * 121. 买卖股票的最佳时机   动态规划
     * dp[i][0] 表示第 i 天持有股票所得的最多现金
     * - 第 i 天买入   dp[i][0] = -price[i]
     * - 第 i 天之前就已经买入  dp[i][0] = dp[i-1][0]
     * <p>
     * dp[i][1] 表示第 i 天不持有股票所得的最多现金
     * - 第 i 天卖出   dp[i][1] = price[i] + dp[i-1][0]
     * - 第 i 天之前就已经卖出  dp[i][1] = dp[i-1][1]
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(prices[i] + dp[i][0], dp[i - 1][1]);
        }
        return dp[n - 1][1];
    }

    /**
     * 122. 买卖股票的最佳时机 II    可以多次买入卖出
     * <p>
     * dp[i][0] 表示第 i 天持有股票所得的最多现金
     * - 第 i 天买入   dp[i][0] = dp[i-1][1] - price[i]
     * - 第 i 天之前就已经买入  dp[i][0] = dp[i-1][0]
     * <p>
     * dp[i][1] 表示第 i 天不持有股票所得的最多现金
     * - 第 i 天卖出   dp[i][1] = price[i] + dp[i-1][0]
     * - 第 i 天之前就已经卖出  dp[i][1] = dp[i-1][1]
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(prices[i] + dp[i - 1][0], dp[i - 1][1]);
        }
        return dp[n - 1][1];
    }

    /**
     * 123. 买卖股票的最佳时机 III
     *
     * @param prices
     * @return
     */
    public int maxProfit6(int[] prices) {
        /*
        dp[i][j] 第 i 天，状态为 j 剩下现金的最大值
        j 有 5 中状态 0~4 分别是 还没有做出买入卖出操作，已经第一次买入，已经第一次卖出，已经第二次买入，已经第二次卖出
        在任意一天结束之后，我们会处于以上五个状态中的一种：
        dp[i][0] = 0;
        dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
        dp[i][2] = max(dp[i-1][1] + prices[i], dp[i-1][2])
        dp[i][3] = max(dp[i-1][2] - prices[i], dp[i-1][3])
        dp[i][4] = max(dp[i-1][3] + prices[i], dp[i-1][4])
         */
        int n = prices.length;
        int[][] dp = new int[n][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[n - 1][4];
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] dp = new int[n][2 * k + 1];
        dp[0][0] = 0;
        for (int i = 0; i < 2 * k; i += 2) {
            dp[0][i + 1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j < 2 * k + 1; j++) {
                int sign = j % 2 == 1 ? -1 : 1;
                dp[i][j] = Math.max(dp[i - 1][j - 1] + sign * prices[i], dp[i - 1][j]);
            }
        }
        return dp[n - 1][2 * k];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * dp[i][j] 依旧表示第 i 天结束的时候，处于状态 j 剩余的最大现金总数；那么如何推导呢
     * 数一下有几种状态
     * 持有股票和不持有股票；不持有股票又分为是冷冻期和非冷冻期
     * - dp[i][0] = max(dp[i-1][0], dp[i-1][2] - prices[i]) max(之前就持有，今天买入持有但前一天是非冷冻期)
     * - dp[i][1] = dp[i-1][0] + prices[i]  今天结束的时候，是冷冻期，说明当天卖出了
     * - dp[i][2] = max(max(dp[i-1][1], dp[i-1][2]))    今天结束的时候，不是冷冻期，说明今天没卖出
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * dp[i][0] 表示持有股票时剩余的最大金额
     * dp[i][1] 表示不持有股票时剩余的最大金额
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] - prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i] - fee)
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[n - 1][1];
    }

    /**
     * 300. 最长递增子序列
     * dp[i] 前 i 个数中最长递增子序列为 dp[i]
     * nums[i]>max dp[i] = dp[i-1] + 1
     * max>nums[i]>min dp[i] = dp[i-1] max = nums[i]        推不出来
     * <p>
     * <p>
     * dp[i] 表示，以 nums[i] 结尾的最长递增子序列的长度
     * 暴力做法，dp[i] 由 dp[j] 推导过来，j < i，
     * 注意，这里是必选 nums[i]，不能不选
     * dp[i] = max(dp[j]) + 1; 0 < j < i nums[i] > nums[j]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // dp[i] 是非常有可能大于 dp[j] + 1 的;这一步是为了选出最大的 dp[j]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 300. 最长递增子序列
     * 维护一个数组 dp[len] 表示最长递增序列的长度为 len 时，子序列最后一个数的最小值
     * <p>
     * dp[len] 不是由前值直接推到过来的，而是在遍历数组的过程中维护的。
     * 我们知道 dp[len] 天生单调递增，
     * 因为如果 dp[3] > dp[4]，那么直接在长度为 4 的最长子递增序列中取前三个元素，最后一个元素 < 第四个元素 < dp[3]
     * 那 dp[3] 就不符合定义了
     * <p>
     * 遍历的过程中，nums[i] > dp[len] 那么 len 就可以加一位了，序列最后一位也变了 dp[len + 1] = nums[i]
     * 如果 nums[i] < dp[len] 那么在 dp 数组中找到 dp[j] > nums[i] 且 dp[j - 1] < nums[i] 的 j ，让 dp[j] = nums[i]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        // dp 数组的长度怎么定义呢，应该是 nums 的长度
        int n = nums.length, len = 1;
        int[] dp = new int[n + 1];
        // dp[0] 没有意义
        dp[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > dp[len]) {//4,10,4,3,8,9
                // 只有走到了这里，最长子序列的长度才会增加
                dp[++len] = nums[i];
            } else {
                // 这里是来维护 dp 的，使 dp 数组满足定义，保持最后一位是最小值
                int left = 1, right = len;
                int mid = 1;
                // 循环不变量，左右都能取
                while (left < right) {
                    mid = left + right >> 1;
                    int midVal = dp[mid];
                    if (nums[i] > midVal) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                dp[left] = nums[i];
            }
        }
        return len;
    }

    /**
     * 674. 最长连续递增序列
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        // dp 数组的含义 dp[i] 表示以第 i 个数字结尾的连续递增序列的长度
        int n = nums.length;
        int max = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 718. 最长重复子数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // dp[i][j] 表示 nums1 以位置 i 的元素结尾，与 nums2 以位置 j 的元素结尾 的两个子数组相同的最长长度
        // 如果 nums1[i] == nums2[j] , dp[i][j] = dp[i-1][j-1] + 1 否则 dp[i][j] = 0
        int[][] dp = new int[n][m];
        // 初始值肯定全部都是 0 了。从第一位往后面遍历

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            if (nums1[0] == nums2[i]) {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 1143. 最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] 表示以 text1[i] 和 test2[j] 结尾的子序列的最长公共子序列的长度
        // dp[i][j] 至于左上角有关，当前元素相等 就加一，不相等就看两个子序列各取 i 和 j 位置元素的最长公共子序列长度
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n][m];
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                index = i;
                dp[i][0] = 1;
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < n; i++) {
                dp[i][0] = 1;
            }
        }
        flag = false;
        for (int i = 0; i < m; i++) {
            if (text2.charAt(i) == text1.charAt(0)) {
                index = i;
                dp[0][i] = 1;
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < m; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 如果最后一位相等，就等于左上角的值，再加上一
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果最后一位不相等，就分别看 test1 少一位和 test2 少一位
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.print("\n");
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 1035. 不相交的线
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // 跟求最长相等子序列是一模一样的问题
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[0]) {
                index = i;
                dp[i][0] = 1;
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < n; i++) {
                dp[i][0] = 1;
            }
        }
        flag = false;
        for (int i = 0; i < m; i++) {
            if (nums2[i] == nums1[0]) {
                index = i;
                dp[0][i] = 1;
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < m; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // dp[i] 表示以第 i 位元素为结尾的连续子数组的最大和
        // dp[i] 的值只有两种情况，
        // - 加入到 dp[i-1] 中
        // - 独立成为一个子数组
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        // 突然想到，这不还是求最长相等子序列吗，最后只要结果 == s.length 就算成功
        int n = s.length();
        int m = t.length();
        if (n == 0) {
            return true;
        }
        if (m == 0) {
            return false;
        }
        // dp[i][j] 表示 s 以 i 位置元素为结尾，t 以 j 位置元素为结尾的两个子串的最长相等子序列的长度
        // dp[i][j] 的值由前值可以推断过来
        int[][] dp = new int[n][m];
        boolean flag = false;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                index = i;
                flag = true;
                dp[i][0] = 1;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < n; i++) {
                dp[i][0] = 1;
            }
        }
        flag = false;
        for (int i = 0; i < m; i++) {
            if (t.charAt(i) == s.charAt(0)) {
                index = i;
                flag = true;
                dp[0][i] = 1;
                break;
            }
        }
        if (flag) {
            for (int i = index + 1; i < m; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n - 1][m - 1] == n;
    }


    public boolean isSubsequence2(String s, String t) {
        // 如果 s 是 t 的子序列，从前往后挨个儿匹配一定匹配的到，假使字符重复出现，
        // 如果使用后出现的能满足条件，使用先出现的一定也能满足条件
        int n = s.length();
        int m = t.length();
        int l = 0, r = 0;
        while (l < n && r < m) {
            if (s.charAt(l) == t.charAt(r)) {
                l++;
            }
            r++;
        }
        return l == n;
    }

    /**
     * 115. 不同的子序列
     * dp[i][j] 表示 s 中以 i-1 结尾的子序列中出现 t 中以 j-1 结尾的个数
     * dp 如何推导
     * <p>
     * - s[i] != t[j] 说明在匹配的时候 s 中的 i 元素用不到，dp[i][j] = dp[i-1][j]
     * - s[i] == t[j] 又分了两种情况，i 元素可选可不选
     * -- 不选 i 元素，dp[i][j] = dp[i-1][j]
     * -- 选 i 元素，就代表着 s 必选 i 元素，t 必选 j 元素，就相当于 s 和 t 都不选最后一个元素的情况，
     * 找到子序列，然后都在最后加上最后一个元素，出现的个数是相等的dp[i][j] = dp[i-1][j-1]
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n][m];
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < m; i++) {
            // s 的长度小于 t，结果只能是 0 ，不作处理
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == t.charAt(i)) {
                    dp[j][i] = dp[j - 1][i] + dp[j - 1][i - 1];
                } else {
                    dp[j][i] = dp[j - 1][i];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 583. 两个字符串的删除操作
     * <p>
     * dp[i][j] 表示 word1 以 i-1 位为结尾，word2 以 j-1 为为结尾，变成相同所需要的最小步数
     * <p>
     * 判断 word1[i-1] 和 word[j-1]
     * - word1[i-1] == word[j-1]; 那么最后两位不用处理了，dp[i][j] = dp[i-1][j-1]
     * - word1[i-1] != word[j-1]; 可选取正上方和正左方的情况的最小步数 + 1;
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        // dp[0][0] 表示两个空字符串
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 72. 编辑距离
     * 简单分析一下，最多操作数肯定是将 s 串从头开始按照 t 串字符来进行替换操作，
     * 然后删掉 s 串多余的字符（假设 s.length > t.length）
     * <p>
     * 那么怎么减少操作次数呢，如果 t 串是 s 串的子序列，那么显然操作次数最少，只需要删除多余的即可
     * 也就是只需要 s.length - t.length 次
     * <p>
     * 如果不是子序列呢，可以求最长相等子序列。情况太多了，直接从 dp 数组的定义上入手
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        // dp[i][j] 表示 word1 前 i 个字符组成的子串和 word2 前 j 个字符组成的子串，前者变成后者需要操作的最小步骤数
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i < m + 1; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 647. 回文子串
     * 这题也能动归的？主要是太巧妙了，dp 数组的含义一出来，大致上就知道怎么做了
     * dp[i][j] 表示以 i 开头，j 结尾的子串是不是回文子串，如果是，用 1 表示，否则，用 0 表示
     * 怎么推导 dp[i][j] 呢，显然首先需要比较子串的头尾两个字符是否相等
     * - s[i] == s[j] 有可能成为回文子串，只要除开首尾两个子串，中间的子串是回文子串就行。故 dp[i][j] == dp[i+1][j-1]
     * - s[i] != s[j] 显然，不可能成为回文子串了
     * 接下来就好做了，可以看到 dp 数组是由左下角推断过来的，初始化和遍历的方向也就很清楚了
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        //  显然 i > j 时，由 dp[i][j] 的定义知道，dp[i][j] 不存在，记为 0;
        //  但是 dp[i-1][i] 由 dp[i][i-1] 推倒而来的，不能简单的把没定义的 dp 初始化为 0 ，
        //  反对角线下面一条斜线也需要初始化为 1
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][i] = 1;
            dp[i][i - 1] = 1;
        }
        int sum = 0;
        // 从下往上，从左往右遍历
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }
                sum += dp[i][j];
            }
        }
        return sum + n;
    }

    /**
     * 516. 最长回文子序列
     * dp[i][j] 表示以 s[i] 开始 s[j] 结尾的子串中最长回文字序列的长度
     * - s[i] == s[j];去掉头尾元素的子串的最长回文字序列长度 ，加上 2 就是 dp[i][j];dp[i][j] = dp[i+1][j-1] + 2
     * - s[i] != s[j];考虑加上 s[i] 或者 s[j] 元素得到的子串的最长回文字序列，再取最大值
     * <p>
     * 其实这也是分了四种情况
     * - 取 i j         对应 s[i] == s[j]
     * - 不取 i j       被包含于下面两种情况，所以下面两种情况取最大值，就包含了这种情况
     * - 取 i 不取 j     s[i] != s[j]     dp[i][j] = dp[i][j-1]
     * - 取 j 不取 i     s[i] != s[j]     dp[i][j] = dp[i+1][j]
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // 也是需要从下往上，从左往右遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        TestDP testDP = new TestDP();
//        testDP.numTrees(3);
//        int[] arr = new int[3];
//        System.out.println(arr[2]);
//        testDP.zeroOnePackage();
//        testDP.change(5, new int[]{5, 2, 1});
//        testDP.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6});
//        testDP.lengthOfLIS2(new int[]{4, 10, 4, 3, 8, 9});
        testDP.longestCommonSubsequence("hofubmnylkra", "pqhgxgdofcvmr");
    }

}
