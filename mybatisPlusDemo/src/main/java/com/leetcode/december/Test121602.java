package com.leetcode.december;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author fdn
 * @since 2021-12-16 15:54
 */
public class Test121602 {
    class MyStack {
        Queue<Integer> queue;
        Queue<Integer> temp;

        public MyStack() {
            queue = new ArrayDeque();
            temp = new ArrayDeque();
        }

        public void push(int x) {
            temp.add(x);
            while (!queue.isEmpty()) {
                temp.add(queue.poll());
            }
            while (!temp.isEmpty()) {
                queue.add(temp.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
