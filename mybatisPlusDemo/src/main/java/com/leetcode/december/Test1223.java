package com.leetcode.december;


import java.util.*;

/**
 * @author fdn
 * @since 2021-12-23 11:10
 */
public class Test1223 {

    /**
     * 46. 全排列
     * <p>
     * 全排列取得应该还是树枝，到叶子节点之后取树枝
     * <p>
     * 元素不能重复取，还是得需要startIndex来记录位置，
     * 但是有了startIndex，元素就只能从前往后取，因为是全排列，所以元素是可以先取后面再取前面的。startIndex不好控制了
     * 用Set去重怎么样。
     * 还是用used数组来保证元素不被重复选取
     *
     * @param nums
     * @return
     */
    List<List<Integer>> result;
    List<Integer> path;
    int[] used;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        used = new int[nums.length];
        backTracing(nums, used);
        return result;
    }

    private void backTracing(int[] nums, int[] used) {
        // 收集结果
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 每个树枝都要遍历全部的元素，不需要剪枝吧
        // 每次遍历都是从 i==0 开始，配合used数组，实现既不重复取，又做到元素选取的顺序可以随意
        for (int i = 0; i < nums.length; i++) {
            // 表示这个元素在这一树支上使用过，其他人碰到了就不要选
            if (used[i] == 1) {
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backTracing(nums, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }

    /**
     * 187. 重复的DNA序列
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        // 定义一个与 s 等长的哈希数组 h[] 和次方数组 p[] （次方数组单纯用来计算底数 p 的次方）
        // h[] 和 p[] 是用来帮助计算 以 j 为结尾的窗口中字符串的 hashcode
        // h[i] 记录的是：以 i 为最后一位的前 i+1 长度的字符串的 hashcode
        // 选 31 作为底数 p
        int n = s.length(), P = 31;
        int[] h = new int[n], p = new int[n];
        // 定义一个 list 来存储符合条件的结果
        List<String> result = new ArrayList<>();
        p[0] = (int) Math.pow(P, 0);
        h[0] = s.charAt(0);
        for (int i = 1; i < n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i);
            // 其实 p[i] = (int) Math.pow(P,i)，没必要定义这个数组的
            p[i] = p[i - 1] * P;
        }
        Map<Integer, Integer> map = new HashMap<>(16);
        // 第一个窗口的 hashcode 先放进去，这很重要，因为计算以 j 为结尾的窗口中字符串的 hashcode 的时候是需要计算 h[i-1] 的
        if (n > 9) {
            map.put(h[9], 1);
        }
        // 10 是目标子串的长度
        for (int i = 1; i + 9 < n; i++) {
            // i 是窗口的第一位，j 是窗口的最后一位
            int j = i + 9;
            // 比如 36895 要求第 2-4 位的数值 hash = 36895 - 36 * 10 ^ 3 = 895
            // 以 j 为结尾的窗口中字符串的 hashcode 为
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            int count = map.getOrDefault(hash, 0);
            // 说明这个 hashcode 出现过，也即他所代表的字符串出现过，加入结果;也可以避免出现两次的时候又把子串加进了结果的情况
            if (count == 1) {
                result.add(s.substring(i, j + 1));
            }
            // 不管出没出现过，都要放到 map 里面去
            map.put(hash, count + 1);
        }
        return result;
    }


    /**
     * 1044. 最长重复子串
     * <p>
     * 题目要求得「能取得最大长度的任一方案」，首先以「最大长度」为分割点的数轴具有「二段性」：
     * 小于等于最大长度方案均存在（考虑在最大长度方案上做删减）；
     * 大于最大长度的方案不存在。
     *
     * @param s
     * @return
     */
    long[] h, p;

    public String longestDupSubstring2(String s) {
        int P = 1313131, n = s.length();
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        String ans = "";
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            String t = check2(s, mid);
            // 说明滑动窗口的 len 还可以更大
            if (t.length() != 0) l = mid;
            else r = mid - 1;
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }

    /**
     * 记录长度为 len 的滑动窗口表示的子串，是否在 s 中出现过两次，如果有，返回该子串，否则返回 ""
     *
     * @param s
     * @param len
     * @return
     */
    String check2(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) return s.substring(i - 1, j);
            set.add(cur);
        }
        return "";
    }

    /**
     * @param s
     * @return
     */
    public String longestDupSubstring(String s) {
        // h[i] 表示字符串 s 第 i 位（包括）前的子串的 hashcode
        int[] h, p;
        int P = 13131;
        int n = s.length();
        h = new int[n + 1];
        p = new int[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        // 找到重复出现的子串，长度从 0~n-1,找到最大的长度
        int l = 0, r = n - 1;
        String max = "";
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            // 字符串 check 有可能不为 "" 又有可能为 ""
            // 因为是通过 check() 来试探满不满足条件
            String check = check(h, p, s, mid);
            if (check.length() != 0) {
                // mid 满足条件，重复出现的子串还可以更长
                l = mid;
            } else {
                r = mid - 1;
            }
            max = check.length() > max.length() ? check : max;
        }
        return max;
    }

    /**
     * 判断长度为 mid 的滑动窗口中 是否在串 s 中出现过两次
     *
     * @param h
     * @param p
     * @param s
     * @param mid
     * @return
     */
    private String check(int[] h, int[] p, String s, int mid) {

        Set<Integer> set = new HashSet<>();
        // i 表示的是 s 的第几位，不是 s 的索引
        for (int i = 1; i + mid - 1 <= s.length(); i++) {
            int j = i + mid - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(hash)) {
                return s.substring(i - 1, j);
            }
            set.add(hash);
        }
        return "";
    }


    public static void main(String[] args) {
        Test1223 test1223 = new Test1223();
//        test1223.permute(new int[]{1, 2, 3});
        System.out.println("AAAAACCCCC".hashCode());
        System.out.println("AAAACCCCCA".hashCode());
        System.out.println("CCCCCAAAAA".hashCode());
        System.out.println("CCCCCCAAAA".hashCode());

        new Integer(123).hashCode();

        System.out.println(Math.pow(3, 4));
        int[] h = new int[3];
        System.out.println(h[0]);
//        test1223.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        test1223.longestDupSubstring("banana");
    }
}
