package com.leetcode.newyear.interview21;

/**
 * @author fdn
 * @since 2022-03-04 09:37
 */
public class RBTree<k extends Comparable<k>, v> {

    // 自定义比较器
    private Comparable<k> comparable;

    private int size;

    private static final boolean RED = false;
    private static final boolean BLACK = true;


    static class Node<k extends Comparable<k>, v> {
        k key;
        v value;
        Node<k, v> left;
        Node<k, v> right;
        Node<k, v> parent;
        boolean RED = BLACK;


    }


}
