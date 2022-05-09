package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-18 09:39
 */
public class Test0218 {

    /**
     * 1791. 找出星型图的中心节点
     *
     * @param edges
     * @return
     */
    public int findCenter(int[][] edges) {
        return (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) ? edges[0][0] : edges[0][1];
    }
}
