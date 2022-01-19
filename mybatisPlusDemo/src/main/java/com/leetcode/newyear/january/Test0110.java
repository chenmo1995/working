package com.leetcode.newyear.january;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2022-01-10 09:40
 */
public class Test0110 {

    /**
     * 306. 累加数
     * a + b = c;a 或者 b 长度大于等于 num.length() 的一半，就不可能
     * <p>
     * 第一个数的分割点作为第一层循环的循环变量。第二个数的分割点作为第二层循环的循环变量
     *
     * @param num
     * @return
     */
    String num = "";
    // 累加数中的单个数 a b c
    List<List<Integer>> list;

    public boolean isAdditiveNumber(String num) {
        list = new ArrayList<>();
        this.num = num;
        return backTracing(0);
    }

    private boolean backTracing(int startIndex) {
        int size = list.size();
        // 终止条件，也是收集时机，遍历到了最后一位的下一位
        if (startIndex == num.length()) {
            // 必须至少包含三个数
            return size >= 3;
        }
        // 如果该位是 0 ；则这个数只能是 0 ，不会继续往后遍历
        int end = num.charAt(startIndex) == '0' ? startIndex + 1 : num.length();

        // 构造 nums （a,b,c）
        List<Integer> cur = new ArrayList<>();
        // 单次循环
        for (int i = startIndex; i < end; i++) {
            // 倒序构造 list；比如 35，构造成 [5, 3]
            cur.add(0, num.charAt(i) - '0');
            // 除去 a, b 与新构造的 c 对比；
            // 如果 a + b == c,把 c 加入 list 中作为下一次的 加数或者被加数
            // 否则，c 选取的就不对，应该加一位，继续。
            // 剪枝，单个 a,b 的长度不可能大于 num.length() >> 1 。否则， a+b==c 一定不成立
            if (cur.size() > num.length() >> 1) {
                return false;
            }
            if (size < 2 || check(list.get(size - 2), list.get(size - 1), cur)) {
                list.add(cur);
                if (backTracing(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    private boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> sum = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) {
                t += a.get(i);
            }
            if (i < b.size()) {
                t += b.get(i);
            }
            // 个位数
            sum.add(t % 10);
            // 当作进位给下一位加法
            t /= 10;
        }
        // 最后面的进位
        if (t > 0) {
            sum.add(t);
        }
        if (sum.size() != c.size()) {
            return false;
        }
        for (int i = 0; i < c.size(); i++) {
            if (!sum.get(i).equals(c.get(i))) {
                return false;
            }
        }
        return true;
    }


}
