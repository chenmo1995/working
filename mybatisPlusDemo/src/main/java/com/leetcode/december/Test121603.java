package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-16 16:17
 */
public class Test121603 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            Character peek = stack.peek();
            if (peek == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return Arrays.stream(stack.toArray()).map(String::valueOf).reduce("", (a, b) -> a + b);
    }


    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (!"+".equals(tokens[i]) && !"-".equals(tokens[i]) && !"*".equals(tokens[i]) && !"/".equals(tokens[i])) {
                stack.push(tokens[i]);
            } else {
                if ("+".equals(tokens[i])) {
                    String a = stack.pop();
                    String b = stack.pop();
                    stack.push(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b)));
                } else if ("-".equals(tokens[i])) {
                    String a = stack.pop();
                    String b = stack.pop();
                    // 注意减法和除法，两个数的顺序
                    stack.push(String.valueOf(Integer.parseInt(b) - Integer.parseInt(a)));
                } else if ("*".equals(tokens[i])) {
                    String a = stack.pop();
                    String b = stack.pop();
                    stack.push(String.valueOf(Integer.parseInt(a) * Integer.parseInt(b)));
                } else if ("/".equals(tokens[i])) {
                    String a = stack.pop();
                    String b = stack.pop();
                    stack.push(String.valueOf(Integer.parseInt(b) / Integer.parseInt(a)));
                }
            }
        }
        return Integer.parseInt(stack.peek());
    }


    /**
     * 239. 滑动窗口最大值
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] max = new int[nums.length - k + 1];
        // 构造优先队列（里面维护一个队列的最大值，元素是 int[滑动窗口的数值，该数值在原数组中的位置]），自定义了排序规则
        // 排序规则：二元数组的第一个值排倒序，第一个值相同就按照第二个值排倒序，第二个值是（滑动窗口的数值在原数组中的位置）
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        // 维护初试优先队列
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        // 滑动窗口开始移动
        max[0] = pq.peek()[0];
        for (int i = k; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] < i - k + 1) {
                pq.poll();
            }
            max[i - k + 1] = pq.peek()[0];
        }
        return max;
    }


    /**
     * 239. 滑动窗口最大值
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        // 维护一个初始的单调递减的双端队列,注意，双端队列里面存储的是nums中元素的位置，递减指的是nums中该位置的元素的值递减
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] max = new int[nums.length - k + 1];
        max[0] = nums[deque.peekFirst()];
        // 滑动窗口开始移动
        for (int i = k; i < nums.length; i++) {
            // 继续维护单调递减双端队列
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 取出最大值
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            max[i - k + 1] = nums[deque.peekFirst()];
        }
        return max;
    }


}






















