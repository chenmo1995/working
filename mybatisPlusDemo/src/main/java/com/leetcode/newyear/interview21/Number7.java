package com.leetcode.newyear.interview21;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * 将「有效括号问题」转化为「分值有效性」的数学判定，算是三叶比较习惯的一种做法。
 * 事实上，不仅仅是括号问题。此类「成对匹配」相关的题型都能转化为此类模型进行求解。
 *
 * @author fdn
 * @since 2022-01-28 15:09
 */
public class Number7 {

    StringBuilder path;
    List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        path = new StringBuilder();
        backTracing(0, 0, n);
        return result;
    }

    /**
     * @param left  左括号个数
     * @param right 右括号个数
     * @param n
     */
    private void backTracing(int left, int right, int n) {
        // 左括号数不能比右括号少
        if (left < right || left > n) {
            return;
        }
        // 终止条件肯定是 path.length == 2 * n 了
        if (path.length() == 2 * n) {
            result.add(path.toString());
            return;
        }
        // 每一层可选择的元素只有两个
        String[] arr = new String[]{"(", ")"};

        // 同时括号的数量要满足一定条件，左右括号数都不能大于 n
        for (int i = 0; i < arr.length; i++) {
            path.append(arr[i]);
            if (i == 0) {
                left++;
            } else {
                right++;
            }
            backTracing(left, right, n);
            path.deleteCharAt(path.length() - 1);
            if (i == 0) {
                left--;
            } else {
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;

        /**
         * 0b 0111 1111
         * 0b 1000 0000
         *
         * 0b 1111 1111
         *
         */

        System.out.println(max + 1);

        /**
         * 0b 0000 0101;
         * 0b 1111 1010;
         * 0b 1111 1001;
         * 0b 1000 0110
         *
         * 0b 1000 0110;
         * 0b 1000 0101
         *
         * 0b 1000 0110;
         * 0b 1111 1010
         */
        int a = 5;
        int b = ~a;
        System.out.println(b);

        int m = 0b10000000000000000000000000000000;
        System.out.println(m);
        int n = 0b11111111111111111111111111111111;
        System.out.println(n);
    }
}
