package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * @author fdn
 * @since 2022-04-24 16:13
 */
public class CombinationOfPhoneNumber {

    List<String> result = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    int n;
    static Map<Character, char[]> map = new HashMap();

    static {
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        n = chars.length;
        if (n == 0) {
            return result;
        }
        backTracing(chars, 0);
        return result;
    }

    private void backTracing(char[] chars, int startIndex) {
        if (path.length() == n) {
            result.add(new String(path));
            return;
        }
        char c = chars[startIndex];
        char[] chars1 = map.get(c);
        for (int i = 0; i < chars1.length; i++) {
            path.append(chars1[i]);
            backTracing(chars, startIndex + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }


    class Test {
        /**
         * 17. 电话号码的字母组合
         * <p>
         * 深度k可以理解为字符串中数字的个数，
         * 宽度n呢，应该就是每个数字对应的字母个数吧,3或者4
         *
         * @param digits
         * @return
         */
        List<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        Map<Character, char[]> map = new HashMap<>();

        public List<String> letterCombinations(String digits) {
            map.put('2', new char[]{'a', 'b', 'c'});
            map.put('3', new char[]{'d', 'e', 'f'});
            map.put('4', new char[]{'g', 'h', 'i'});
            map.put('5', new char[]{'j', 'k', 'l'});
            map.put('6', new char[]{'m', 'n', 'o'});
            map.put('7', new char[]{'p', 'q', 'r', 's'});
            map.put('8', new char[]{'t', 'u', 'v'});
            map.put('9', new char[]{'w', 'x', 'y', 'z'});
            if (digits.length() == 0) {
                return result;
            }
            backTracing(0, digits);
            return result;
        }

        private void backTracing(int startIndex, String digits) {
            // 终止条件，path的长度为k
            if (path.length() == digits.length()) {
                result.add(new String(path));
                return;
            }
            // 回溯的遍历过程
            char c = digits.charAt(startIndex);
            for (int i = 0; i < map.get(c).length; i++) {
                path.append(map.get(c)[i]);
                backTracing(startIndex + 1, digits);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}
