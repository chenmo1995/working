package com.leetcode.newyear.january;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-01-28 10:00
 */
public class Test0128 {

    /**
     * 1996. 游戏中弱角色的数量
     * 给 properties 的攻击力进行倒序，这样保证【当前遍历的元素的强元素只存在于此之前】
     * 如果出现攻击力相同的一批元素，我们选择按照防御力排升序，并记录遍历过程中的最大防御力
     * <p>
     * 对于不同批，只要当前元素防御力比最大防御力小，同时有 最大防御力的元素的攻击力在他之前，所以遍历的元素就是 弱元素
     * 对于同一批，当前元素防御力不可能比之前防御力小（因为按防御力升序排列）
     *
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int maxDefense = 0;
        int count = 0;
        for (int[] property : properties) {
            if (property[1] < maxDefense) {
                count++;
            } else {
                maxDefense = property[1];
            }
        }
        return count;
    }
}
