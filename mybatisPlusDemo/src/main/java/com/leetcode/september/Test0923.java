package com.leetcode.september;

/**
 * @author fdn
 * @since 2021-09-23 16:48
 */
public class Test0923 {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        int i = 0;
        double result = 0;
        while ((result = Math.pow(3, i)) < n) {
            i++;
        }
        return result == n;
    }

    public boolean isPowerOfThree2(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }

    int[] tri = new int[40];

    public int tribonacci(int n) {
        if (n == 0) {
            tri[0] = 0;
        }
        if (n == 1) {
            tri[n] = 1;
        }
        if (n == 2) {
            tri[n] = 1;
        }
        if (n >= 3) {
            tri[n] = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }
        return tri[n];
    }

    void getNums(int n, int[] nums) {

        nums[n] = nums[n - 1] + nums[n - 2] + nums[n - 3];
    }

    void getNums2(int n, int[] nums) {
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                nums[i] = 0;
            }
            if (i == 1) {
                nums[i] = 1;
            }
            if (i == 2) {
                nums[i] = 1;
            }
            if (i >= 3) {
                nums[i] = nums[i - 1] + nums[i - 2] + nums[i - 3];
            }
        }
    }
}
