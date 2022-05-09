package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-22 13:31
 */
public class Test0122 {

    /**
     * 1332. 删除回文子序列
     * 如果是普通字符组成的话，可以每次删除一个最长回文子序列吗
     * <p>
     * 但是为啥要强调只有 a b 两个字母组成呢；
     * 淦 ！第一次把 a 全部删掉，第二次把 b 全部删掉不就行了？
     * <p>
     * 所以题目的目的是问 s 是不是回文字符串，如果是，那么就只删一次，不是，就需要删两次
     *
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left++) == s.charAt(right--)) {
                continue;
            } else {
                return 2;
            }
        }
        return 1;
    }
}
