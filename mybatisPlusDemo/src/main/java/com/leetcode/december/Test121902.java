package com.leetcode.december;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-20 00:03
 */
public class Test121902 {

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    List<List<String>> result;
    List<String> path;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        backTracing(s, 0);
        return result;
    }

    /**
     * @param s
     * @param startIndex 下一轮递归便利的起始位置，
     */
    void backTracing(String s, int startIndex) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 截取[startIndex,i]，注意切割的是闭区间
            if (isPalindrome(startIndex, i, s)) {
                // 是回文，加到path中，进行下一层递归，继续切割
                path.add(s.substring(startIndex, i + 1));
                backTracing(s, i + 1);
                path.remove(path.size() - 1);
            } else {
                // 不是回文，放弃本次切割，进行下一轮切割尝试
                continue;
            }
        }

    }

    /**
     * startIndex == i 也算做回文字符串
     *
     * @param startIndex
     * @param i
     * @param s
     * @return
     */
    private boolean isPalindrome(int startIndex, int i, String s) {
        while (startIndex < i) {
            if (s.charAt(startIndex++) != s.charAt(i--)) {
                return false;
            }
        }
        return true;
    }
}



















