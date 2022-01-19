package com.leetcode.december;

import java.util.Stack;

/**
 * @author fdn
 * @since 2021-12-16 15:28
 */
public class Test1216 {
    class MyQueue {
        Stack<Integer> stack;
        Stack<Integer> temp;

        public MyQueue() {
            stack = new Stack<>();
            temp = new Stack<>();
        }

        public void push(int x) {
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
            temp.push(x);
            while (!temp.empty()) {
            stack.push(temp.pop());
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
