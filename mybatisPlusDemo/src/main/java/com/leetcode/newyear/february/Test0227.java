package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-27 20:35
 */
public class Test0227 {
    /**
     * 553. 最优除法
     * 数越除越小；所以要求左边最大，右边最小
     * 所以前面只留一个，剩余的数都留在后面
     * 对于后面的数，要求值最小，那么就是前面最小，后面最大，也即后面只留一个，其余的都留在前面
     *
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nums[i]).append("/");
        }
        String substring = sb.substring(0, sb.length() - 1);
        if (n > 2) {
            substring = substring.substring(0, substring.indexOf("/") + 1)
                    + "("
                    + substring.substring(substring.indexOf("/"))
                    + ")";
        }
        return substring;
    }

    /**
     * @param nums
     * @return
     */
    public String optimalDivision2(int[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nums[i]);
            if (i < n - 1) {
                sb.append("/");
            }
        }
        if (n > 2) {
            sb.insert(sb.indexOf("/") + 1, "(");
            sb.append(")");
        }
        return sb.toString();
    }
}
