package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 这也是一个切割的问题
 * 依旧是列举出所有的切割情况，再判断是否符合 ip 地址的特性
 *
 * @author fdn
 * @since 2022-04-24 21:36
 */
public class RestoreIpAddress {
    public static void main(String[] args) {
        RestoreIpAddress test = new RestoreIpAddress();
        test.restoreIpAddresses("25525511135");
    }

    List<String> result = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    int n;

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        backTracing(s, 0, 0);
        return result;
    }

    private void backTracing(String s, int startIndex, int count) {
        if (startIndex == n && count == 4) {
            result.add(path.substring(0, path.length() - 1));
            return;
        }
        String temp;
        for (int i = startIndex; i < n && Integer.parseInt(temp = s.substring(startIndex, i + 1)) <= 255 && count < 4; i++) {
            // 闭区间 [startIndex, i]
            if (temp.startsWith("0") && temp.length() > 1) {
                break;
            }
            path.append(s.charAt(i));
            path.append(".");
            count++;
            backTracing(s, i + 1, count);
            path.delete(path.lastIndexOf("."), path.length());
            count--;
        }
    }

    private void backTracing2(String s, int startIndex, int count) {
        if (startIndex == n && count == 4) {
            result.add(path.substring(0, path.length() - 1));
            return;
        }
        String temp;
        for (int i = startIndex; i < n && Integer.parseInt(temp = s.substring(startIndex, i + 1)) <= 255 && count < 4; i++) {
            // 闭区间 [startIndex, i]
            if (temp.startsWith("0") && temp.length() > 1) {
                break;
            }
            path.append(temp);
            path.append(".");
            count++;
            backTracing2(s, i + 1, count);
            path.delete(path.length() - 1, path.length());
            path.delete(path.lastIndexOf(".") + 1, path.length());
//            String substring2 = path.substring(0, path.deleteCharAt(path.length() - 1).lastIndexOf(".") + 1);
//            path = new StringBuilder(substring2);
            count--;
        }
    }

    class Test {

        /**
         * 93. 复原 IP 地址
         * <p>
         * s = "25525511135" 你不能重新排序或删除 s 中的任何数字
         *
         * @param s
         * @return
         */
        List<String> result;
        StringBuilder path;

        public List<String> restoreIpAddresses(String s) {
            result = new ArrayList<>();
            path = new StringBuilder();

            backTracing(s, 0, 0);
            return result;
        }


        private void backTracing(String s, int startIndex, int pointCount) {
            if (path.length() == s.length() + 4) {
                result.add(new String(path.deleteCharAt(path.length() - 1)));
                return;
            }

            // 这也是切割
            for (int i = startIndex; i < s.length() && pointCount <= 3; i++) {
                // 切割[startIndex,i],然后检验是否合格，合格就进行下一层递归，不合格 结束本次for循环
                String substring = s.substring(startIndex, i + 1);
                if (isIP(substring)) {
                    path.append(substring).append(".");
                    pointCount++;
                    backTracing(s, i + 1, pointCount);
                    // 注意substring是重新生成新的字符串，不是对原串截取
                    String substring2 = path.substring(0, path.deleteCharAt(path.length() - 1).lastIndexOf(".") + 1);
                    path = new StringBuilder(substring2);
                    pointCount--;
                } else {
                    break;
                }

            }
        }


        private boolean isIP(String substring) {
            if (substring.startsWith("0")) {
                if (substring.length() > 1) {
                    return false;
                }
            } else {
                if (Integer.parseInt(substring) > 255) {
                    return false;
                }
            }
            return true;
        }
    }
}
