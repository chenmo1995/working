package com.leetcode.newyear.weeklyrank;

import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-02-20 10:34
 */
public class Test0220 {

    public int countEven(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            int rest = 0;
            int temp = i;
            while (temp != 0) {
                rest += temp % 10;
                temp /= 10;
            }
            if (rest % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Test0220 test0220 = new Test0220();
//        test0220.countEven(10);
        test0220.repeatLimitedString("cczazcc", 3);
    }


    /**
     * 2181. 合并零之间的节点
     * 题目很简单，但是链表有点绕，写不出来
     * <p>
     * 可以一边遍历原链表一边 new 节点加入新链表的末尾
     *
     * @param head
     * @return
     */
    public ListNode mergeNodes(ListNode head) {
        // 新链表的头结点
        ListNode ans = new ListNode(-1);
        // 新链表的尾节点
        ListNode next = ans;
        while (head != null) {
            if (head.val == 0) {
                // 遍历原链表的指针向后移动
                head = head.next;
            } else {
                ListNode cur = new ListNode(0);
                while (head.val != 0) {
                    cur.val = cur.val + head.val;
                    head = head.next;
                }
                // 加入新链表的尾部
                next.next = cur;
                // 新链表的尾部向后移动
                next = next.next;
            }
        }
        return ans.next;
    }

    /**
     * 6014. 构造限制重复的字符串
     * 统计各字符出现的次数，然后从字典序高到底选取
     *
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
        }
        // 用优先队列存储各个字符出现的次数，方便排序选出字典序最大的字符
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                pq.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        // 记录连续字符出现的次数
        int num = 0;
        while (!pq.isEmpty()) {
            int first = pq.poll();
            // 先处理连续字符个数，后面就不用处理了
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) == (char) (first + 'a')) {
                num++;
            } else {
                num = 1;
            }
            if (num <= repeatLimit) {
                // 已经处理过了连续字符个数
                // 连续字符没超过限制
                sb.append((char) (first + 'a'));
                count[first]--;
                if (count[first] > 0) {
                    pq.add(first);
                }
            } else {
                // 连续字符超过限制
                if (pq.isEmpty()) {
                    return sb.toString();
                } else {
                    int second = pq.poll();
                    sb.append((char) (second + 'a'));
                    count[second]--;
                    num = 1;
                    // 没有使用到 first ，继续入队
                    pq.offer(first);
                    if (count[second] > 0) {
                        pq.offer(second);
                    }
                }
            }
        }
        return sb.toString();
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
