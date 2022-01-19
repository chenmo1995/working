package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-12-30 10:25
 */
public class Test1230 {

    /**
     * 846. 一手顺子
     * 一个数组 hand ，分成若干份，每一份都是顺子
     * 最快想到就是 hand 排序一下，然后按照 【顺子，张数】 为条件慢慢减少 hand
     * 最终恰好 hand 为 0，就可以
     * 慢慢减少 hand 期间，凑不成顺子，就 gg
     * **难点就在怎么在有序数组 hand 中找到我们指定的数，然后去掉它**；好像只能新建个数组然后复制剩下的元素，这样肯定不行
     * <p>
     * 可以利用优先队列，找到最小值，然后自己构建一个数组，每次往里面加元素，最后判断是否与数组 hand 相等
     * 但是每次都要重新构造优先队列，时间复杂度太高了。而且从优先队列中剔除非堆顶元素好像也是无法实现的
     * <p>
     * 全部元素加入到 map 中，剔除就很简单了。
     * 但是求最小值又复杂起来了。
     * **这个想法没毛病，最小值是不好求，但是还是可以使用优先队列。当然优先队列只能剔除堆顶元素。
     * 我们可以结合 map 。如果堆顶元素在 map 中的次数是 0 ，说明他不是剩余数组中元素，弹出，继续取堆顶元素。
     * 这样就可以实现又可以快速剔除元素，且快速找到最小值了**
     * <p>
     * 倒是可以用 list ，删除也可以删，元素顺序也不会变，最小值总是 list.get(0)。
     * 但是但是，时间复杂度就高起来了，因为用了很多集合的 api
     *
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        List<Integer> list = Arrays.stream(hand).boxed().sorted().collect(Collectors.toList());

        while (!list.isEmpty()) {
            Integer first = list.get(0);
            for (int i = 0; i < groupSize; i++) {
                boolean remove = list.remove(new Integer(first + i));
                if (!remove) {
                    return false;
                }
            }
        }
        return true;
    }

}
