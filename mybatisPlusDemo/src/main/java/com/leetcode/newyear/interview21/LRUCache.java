package com.leetcode.newyear.interview21;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 *
 * @author fdn
 * @since 2022-02-22 15:18
 */
public class LRUCache {
    // 记录键值对
    Map<Integer, Integer> map;
    // 通过 key 找 ListNode
    Map<Integer, ListNode> listNodeMap;
    int maxSize;
    int size;
    ListNode head;
    ListNode tail;


    public LRUCache(int capacity) {
        map = new HashMap<>();
        listNodeMap = new HashMap<>();
        head = new ListNode(-1);
        tail = head;
        maxSize = capacity;
        size = 0;
    }

    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        } else {
            int value = map.get(key);
            removeNode(key);
            put(key, value);
            return value;
        }
    }

    /**
     * 删除只会删除头结点,不对，更新中间节点的时候也需要做删除操作
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        ListNode node;
        // 如果 key 在 map 中存在，更新 map，删除链表中该节点，并在尾部添加该节点；将新的节点关系放入 listNodeMap
        if (map.get(key) != null) {
            // 删除指定节点
            removeNode(key);
            // 重新加入
            put(key, value);
        } else {
            // key 不存在，插入尾部，更新 map 和 listNodeMap
            node = new ListNode(key, tail, null);
            tail.next = node;
            // 前一个节点的连接关系已经改变，需要更新 listNodeMap;head 不用put进去
            if (tail != head) {
                listNodeMap.put(tail.val, tail);
            }
            tail = node;
            size++;
            map.put(key, value);
            listNodeMap.put(key, node);
        }

        if (size > maxSize) {
            removeFirst();
        }
    }

    /**
     * 删除指定节点
     *
     * @param key
     */
    private void removeNode(int key) {
        ListNode remove = listNodeMap.get(key);
        // 链表中删除指定节点
        ListNode pre = remove.pre;
        ListNode next = remove.next;
        if (next == null) {
            pre.next = null;
            // 尾节点指针
            tail = pre;
            map.remove(key);
            // 前一个节点调整关系之后更新 listNodeMap
            listNodeMap.remove(key);
            listNodeMap.put(pre.val, pre);
        } else {
            pre.next = next;
            next.pre = pre;
            // map、listNodeMap 中删去
            map.remove(key);
            // 这里注意，前后节点的关系已经改变了
            listNodeMap.remove(key);
            // 头结点不用放，是虚拟节点
            if (pre != head) {
                listNodeMap.put(pre.val, pre);
            }
            listNodeMap.put(next.val, next);
        }
        size--;
    }

    private void removeFirst() {
        removeNode(head.next.val);
    }

    class ListNode {
        int val;
        ListNode pre;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
            pre = null;
            next = null;
        }

        public ListNode(int val, ListNode pre, ListNode next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}
