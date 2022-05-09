package com.leetcode.newyear.february;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-02-10 10:55
 */
public class Test0210 {

    /**
     * 1447. 最简分数
     * 要求所有的，那肯定只能爆搜了
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }
        Set<Double> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (!set.contains((double) i / j)) {
                    // 判断是不是最简，直接看 分数的值 之前有没有出现过
                    result.add(i + "/" + j);
                    set.add((double) i / j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(1.0 / 2.0);
        double v = (double) 1 / 2;
        System.out.println(v);
    }
}
