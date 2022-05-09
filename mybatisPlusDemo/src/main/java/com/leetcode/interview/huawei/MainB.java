package com.leetcode.interview.huawei;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author fdn
 * @since 2022-04-07 21:28
 */
public class MainB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int ans = calculate(s);
        System.out.println(ans);
    }

    /**
     * 1、不会越界
     * 2、只有操作数和操作符
     * 3、保证合法
     * <p>
     * x # y = 2x + 3y + 4
     * x $ y = 3x + y + 2
     * $ 优先级更高
     * 7#6$5#12
     * <p>
     * 没有结合律，必须从前往后
     *
     * <p>
     * 用一个栈记录操作数，另一个栈记录操作符，碰到 $ 就先计算
     *
     * @param s
     * @return
     */
    private static int calculate(String s) {
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        Deque<Integer> num = new ArrayDeque<>();
        Stack<Character> character = new Stack<>();
        for (int i = 0; i < n; i++) {
            char x = s.charAt(i);
            while (i < n && x != '$' && x != '#') {
                sb.append(x);
                i++;
                if (i < n) {
                    x = s.charAt(i);
                }
            }
            num.offerLast(Integer.parseInt(sb.toString()));
            if (!character.isEmpty() && character.peek() == '$') {
                int b = num.pollLast();
                int a = num.pollLast();
                character.pop();
                num.offerLast(cal2(a, b));
            }
            sb = new StringBuilder();
            if (x == '$') {
                character.add('$');
            } else if (x == '#') {
                character.add('#');
            }
        }
        while (num.size() > 1) {
            int a = num.pollFirst();
            int b = num.pollFirst();
            num.offerFirst(cal1(a, b));
        }
        return num.peekFirst();
    }

    /**
     * #
     *
     * @param a
     * @param b
     * @return
     */
    private static int cal1(int a, int b) {
        return 2 * a + 3 * b + 4;
    }

    /**
     * $
     *
     * @param a
     * @param b
     * @return
     */
    private static int cal2(int a, int b) {
        return 3 * a + b + 2;
    }
}
