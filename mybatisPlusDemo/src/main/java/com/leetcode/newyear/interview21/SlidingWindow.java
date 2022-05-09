package com.leetcode.newyear.interview21;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 滑动窗口
 *
 * @author fdn
 * @since 2022-02-09 10:42
 */
public class SlidingWindow {

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 使用分治的思想
     * 如果一个字符 c 在 s 中出现的次数少于 k 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。
     * 所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果：把 s 按照 c 分割（分割后每个子串都不包含 c）
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        // 终止条件
        if (s.length() < k) {
            return 0;
        }
        // <字符，出现次数>
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 将字符放入 map，并记录出现次数
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : map.keySet()) {
            int result = 0;
            // 进入递归
            if (map.get(c) < k) {
                // c 不满足要求了，将字符串 s 按照 c 切割，得到一些子串；
                for (String s1 : s.split(c.toString())) {
                    int count = longestSubstring(s1, k);
                    result = Math.max(result, count);
                }
                return result;
            }
        }
        // 未进入递归
        return s.length();
    }

    /**
     * 643. 子数组最大平均数 I
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double avg = (double) sum / k;

        for (int l = 1, r = k; r < n; l++, r++) {
            sum = sum - nums[l - 1] + nums[r];
            avg = Math.max(avg, (double) sum / k);
        }
        return avg;
    }

    /**
     * 3. 无重复字符的最长子串
     * 求子串，既然是连续的，那肯定得是滑动窗口
     * 移动左右边界的时候还得记录一下窗口中元素的位置，因为当加入一个重复的字符 c，需要将窗口中包括 c 之前的所有字符全部移出去
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0 || n == 1) {
            return n;
        }
        int max = 1;
        for (int l = 0, r = 1; r < n; r++) {
            // 判断窗口中是否有新加入的字符
            char c = s.charAt(r);
            int index = s.substring(l, r).indexOf(c);
            if (index != -1) {
                l = index + 1 + l;
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    /**
     * 480. 滑动窗口中位数
     * 我们维护两个优先队列，一个存储左 k / 2 个小元素（大顶堆），堆顶就是第 (k-1)/2 大的元素
     * 再维护小顶堆，存储右 (k + 1)/ 2 个元素，堆顶就是第 k / 2 大的元素
     * <p>
     * 小技巧：数组求中位数，可以求 k/2 大的值和 (k-1)/2 大的值，避免讨论数组长度 k 的奇偶性
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        // 我们可以保证右半边队列元素跟左边一样或者大一个，这样好求中位数
        // 先给右边队列中入队 k 个元素
        PriorityQueue<Integer> right = new PriorityQueue<>(Integer::compare);//小顶堆
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));//大顶堆
        for (int i = 0; i < k; i++) {
            right.offer(nums[i]);
        }
        // 分出 k/2 个元素给 left ，有可能比 right 少一个元素
        for (int i = 0; i < k / 2; i++) {
            left.offer(right.poll());
        }
        double[] result = new double[nums.length - k + 1];
        result[0] = getMid(left, right);
        for (int i = k; i < nums.length; i++) {
            int add = nums[i], del = nums[i - k];
            // 人为确保了 right 会比 left 多，因此，删除和添加都与 right 比较（left 可能为空）
            if (add >= right.peek()) {
                right.offer(add);
            } else {
                left.offer(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            // 调整左右队列的大小，让他们的长度相等或者右队列长度大 1；方便计算中位数
            adjustSize(left, right);
            result[i - k + 1] = getMid(left, right);
        }
        return result;
    }

    private double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return left.peek() / 2.0 + right.peek() / 2.0;
        } else {
            return right.peek();
        }
    }

    private void adjustSize(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) {
            right.offer(left.poll());
        }
        while (right.size() - 1 > left.size()) {
            left.offer(right.poll());
        }
    }

    /**
     * 187. 重复的DNA序列
     * 容易想到使用字符串哈希来记录某个 DNA 序列的出现
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        // 作为字符串哈希的基数
        int P = 13131;
        // 字符串哈希
        int[] h = new int[n];
        // 次方数组
        int[] p = new int[n];
        p[0] = (int) Math.pow(P, 0);
        h[0] = s.charAt(0) * p[0];
        for (int i = 1; i < n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i);
        }
        // <hash, count>
        Map<Integer, Integer> map = new HashMap<>(16);
        List<String> result = new ArrayList<>();
        if (n > 9) {
            map.put(h[9], 1);
        }
        for (int i = 1; i + 9 < n; i++) {
            // [i, j] 组成长度为 10 的序列
            int j = i + 9;
            int hash = h[j] - h[i - 1] * p[10];
            int count = map.getOrDefault(hash, 0);
            if (count == 1) {
                result.add(s.substring(i, j + 1));
            }
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }
        return result;
    }

    /**
     * 424. 替换后的最长重复字符
     * 最长子字符串，一般使用滑动窗口来做
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        // 维护一个长度为 26 的数组，来记录 [l, r] 区间中各字符出现的次数
        int[] cnt = new int[26];
        int max = 0;
        int l = 0, r = 0;
        while (r < n) {
            int index = s.charAt(r) - 'A';
            cnt[index]++;
            while (!check(cnt, k)) {
                // [l, r] 区间不满足题意了，就对 l 向右移动一位
                index = s.charAt(l) - 'A';
                cnt[index]--;
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

    private boolean check(int[] cnt, int k) {
        int sum = 0, max = 0;
        for (int i : cnt) {
            sum += i;
            max = Math.max(max, i);
        }
        return sum - max <= k;
    }

    /**
     * 1423. 可获得的最大点数
     * <p>
     * 首先可以肯定一点，不能直接比较一行中的首尾两个元素来决定选取开头或者末尾的元素
     * 讲道理，直接遍历就可以吧，按照在开头取 [0 ~ k] 个元素，剩余的在队尾取
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] tail = new int[k + 1];
        for (int i = 0; i < k; i++) {
            tail[0] += cardPoints[i];
        }
        int max = tail[0];
        for (int i = 1; i < k + 1; i++) {
            tail[i] = tail[i - 1] - cardPoints[k - i] + cardPoints[n - i];
            max = Math.max(max, tail[i]);
        }
        return max;
    }

    /**
     * 11. 盛最多水的容器
     * 首先这不是求两个最大值
     * 其次，不能暴力遍历
     * <p>
     * 这题主要思想是贪心，考虑如何求出局部最优解
     * 左右指针分别指向 0，n - 1 的位置，往中间移动；
     * 如何移动呢？肯定是移动高度相对小的指针最好，这样才有可能使移动之后的面积变大
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            while (i > 0 && i < n - 1 && nums[i] == nums[i - 1]) {
                i++;
            }
            int target = -nums[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                if (nums[l] + nums[r] > target) {
                    r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return list;
    }

    /**
     * 16. 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int min = nums[0] + nums[1] + nums[2];
        int gap = Math.abs(min - target);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = n - 1;
            int sum;
            while (l < r) {
                while (l > i + 1 && l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                if (l >= r) {
                    break;
                }
                sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    r--;
                } else {
                    l++;
                }
                if (Math.abs(sum - target) < gap) {
                    min = sum;
                    gap = Math.abs(sum - target);
                }
            }
        }
        return min;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 双指针，先让右指针多跑 n-1 个节点，然后左右指针同时移动，
     * 最后右指针到最后一个元素的时候，左值针的位置就是要删除的节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        int i = 0;
        while (i++ < n) {
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 45. 跳跃游戏 II
     * BFS
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int count = 0;
        // 队列存储元素的下标
        Deque<Integer> deque = new ArrayDeque<>();
        // 记录该位置是否遍历过，防止重复处理
        int[] used = new int[n];
        // 初始元素入队
        used[0] = 1;
        deque.offerLast(0);

        while (!deque.isEmpty()) {
            int size = deque.size();
            // 这一跳所能到达的所有位置
            while (size-- > 0) {
                int cur = deque.pollFirst();
                // 当前处理的元素就是数组的最后一个元素，说明已经跳到了最后一位，返回结果
                if (cur == n - 1) {
                    return count;
                }
                // 下一步能到达的元素的下标
                for (int i = cur + 1; i <= nums[cur] + cur && i < n; i++) {
                    // 还没处理该元素
                    if (used[i] == 0) {
                        used[i] = 1;
                        deque.offerLast(i);
                    }
                }
            }
            // 进行下一跳
            count++;
        }
        return count;
    }

    /**
     * 45. 跳跃游戏 II
     * 定义 dp[i] 为到达第 i 个位置所需要的最小步数；
     * 显然 dp[0] = 0;
     * 当我们要求 dp[i] 的时候，我们需要找到最早能够一步到达 i 点的 j 点 dp[i] = dp[j] + 1;
     * 因为最早到 dp[i] 意味着花费了最小的步数
     * 而这个找 j 的过程可以使用双指针来找
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1, j = 0; i < n; i++) {
            // 只要 j + nums[j] >= i，就说明这个 j 可以到达 i。要找最早的 j ，从前往后遍历即可
            while (j + nums[j] < i) {
                j++;
            }
            dp[i] = dp[j] + 1;
        }

        return dp[n - 1];
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 滑动窗口的思想很容易想到；先统计 p 中各字符的频次（用 int[26] 数组）
     * 然后遍历 s 的过程，也用一个 int[26] 数组来记录滑动窗口中字符出现的频次。O(C*n + m)
     * <p>
     * 上述做法每次检查滑动窗口都要遍历两个数组 int[26]，
     * 其实我们只关心两个数组是否完全一致，所以可以用一个 int[26] 数组来实现；
     * 先记录 p 的 int[26] 数组 cnt ，记录 p 中不同字符的个数为 a。然后遍历滑动窗口的时候，在 cnt 上作处理：
     * - 右边界右移，对 cnt 对应位置做 【抵消】 操作，即 --
     * - 左边界右移，对 cnt 对应位置做 【恢复】 操作，即 ++
     * 用变量 b 记录窗口内与 p 串词频相同的字符的个数。
     * 可得：a == b 就表示窗口有 b 个字符与 p 串出现的频率相等，且 p 串一共就 b 个不同的字符。
     * 也就是 p 串与窗口中字符数量相等，且每个字符出现的频次相等，满足条件。
     * <p>
     * 遍历 s 串的时候，如果窗口执行抵消操作之后，该字符的频率为 0，说明窗口中【多了】一个出现的频率与 p 串中相等的字符
     * 如果窗口执行恢复操作之后，该字符的频率为 1，说明窗口中【少了】一个频率与 p 串相等的字符
     * <p>
     * 即使穿了某个字符的频率为负数了，我们也不用去改变 b 的值，因为滑动窗口中有字符出现次数太多了 (做 -- 操作做多了)，
     * 就必然有字符的出现次数低于 p 串（滑动窗口、p 串长度相等），就必然会导致 b < a
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int n = s.length(), m = p.length();
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            int index = p.charAt(i) - 'a';
            cnt[index]++;
        }
        int a = 0;
        for (int i : cnt) {
            if (i != 0) {
                a++;
            }
        }
        int l = 0, r = 0, b = 0;
        while (r < n) {
            int index = s.charAt(r) - 'a';
            // 先把右边界元素加入
            cnt[index]--;
            if (cnt[index] == 0) {
                b++;
            }
            // 窗口长度大于 m 了，需要弹出左边界元素，移动左边界
            if (r - l + 1 > m) {
                index = s.charAt(l) - 'a';
                cnt[index]++;
                if (cnt[index] == 1) {
                    b--;
                }
                l++;
            }
            if (a == b) {
                list.add(l);
            }
            r++;
        }
        return list;
    }


    /**
     * 413. 等差数列划分
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 0;
        }
        int count = 0;
        int l = 0, r = 1;
        while (r < n) {
            int gap = nums[r] - nums[l];
            while (r + 1 < n && nums[r + 1] - nums[r] == gap) {
                r++;
            }
            // r 是最后一位符合等差的数的 index
            if (r - l + 1 >= 3) {
                count += (r - l - 1) * (r - l) / 2;
            }
            l = r;
            r = l + 1;
        }
        return count;
    }

    /**
     * 443. 压缩字符串
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int length = 0;
        int n = chars.length;
        int l = 0, r = 0;
        while (r < n) {
            while (r < n - 1 && chars[r] == chars[r + 1]) {
                r++;
            }
            chars[length] = chars[r];
            length++;
            int count = r - l + 1;
            if (count != 1) {
                String temp = String.valueOf(count);
                for (int i = 0; i < temp.length(); i++) {
                    chars[length++] = temp.charAt(i);
                }
            }
            r = r + 1;
            l = r;
        }
        return length;
    }

    /**
     * 992. K 个不同整数的子数组
     * 连续子数组，显然是要使用双指针来选取子数组的
     * l = r = 0;r 往右移动，窗口中不同数字的个数为 a；a < k 的时候 r 不断右移；a == k 之后，r 一边右移一边 count++
     * 当出现 a > k 的时候，r 需要回退到第一次 a == k 的时候，同时，l++；
     * r == n-1 的话，l继续移动
     * <p>
     * 实现起来太多细节问题了
     * <p>
     * 换成另一个问题：求 最多由 k 个不同数字组成的子数组的个数 - 最多由 k-1 个不同数字组成的子数组的个数
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return getMaxSubArrays(nums, k) - getMaxSubArrays(nums, k - 1);
    }

    /**
     * 最多有 k 个不同数字组成的子数组的个数
     *
     * @param nums
     * @param k
     * @return
     */
    private int getMaxSubArrays(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;
        // 记录窗口中数字出现的次数，因为 nums[i] <= n,所以把数组长度定为 n+1
        int[] count = new int[n + 1];
        int cate = 0;
        int sum = 0;
        while (r < n) {
            if (count[nums[r]] == 0) {
                // 窗口中数字的种类 +1
                cate++;
            }
            count[nums[r]]++;
            // 窗口不满足要求了，需要右移左边界
            while (cate > k) {
                if (count[nums[l]] == 1) {
                    cate--;
                }
                count[nums[l]]--;
                l++;
            }
            sum += r - l + 1;
            r++;
        }
        return sum;
    }

    /**
     * 519. 随机翻转矩阵
     */
    class Solution {
        int[][] matrix;
        int m;
        int n;
        // 记录有多少位变成了 1
        int count = 0;

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            matrix = new int[m][n];
        }

        public int[] flip() {
            return null;
        }

        public void reset() {
            matrix = new int[m][n];
        }
    }

    /**
     * 1052. 爱生气的书店老板
     * minutes 就是滑动窗口的大小
     * 先把所有不生气的情况下的 customers 数量加起来
     * 然后利用滑动窗口去遍历 customers；
     * - 计算窗口内生气的 customer 总数，记录为最大值 max
     * - 遍历。进入窗口的 grumpy == 0，不对 max 作处理；grumpy == 1，sum += customers[r]
     * - 同时处理出窗口的元素，grumpy == 0，不对 max 做处理；grumpy == 1，sum -= customers[l]
     * 最后记录最大值
     *
     * @param customers
     * @param grumpy
     * @param minutes
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int peaceSum = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                peaceSum += customers[i];
            }
        }
        int l = 0, r = 0;
        int sum = 0;
        while (r < minutes) {
            if (grumpy[r] == 1) {
                sum += customers[r];
            }
            r++;
        }
        int max = sum;
        while (r < n) {// r == 3 原始窗口的下一位
            if (grumpy[r] == 1) {
                sum += customers[r];
            }
            if (grumpy[l] == 1) {
                sum -= customers[l];
            }
            max = Math.max(sum, max);
            r++;
            l++;
        }
        return peaceSum + max;
    }

    /**
     * 524. 通过删除字母匹配到字典里最长单词
     * 先对 s 做预处理，避免后续 s 与 dictionary 中每一个字符串做匹配时都要把 s 遍历一边
     * dp[i][j] 表示从位置 i 开始，往后第一次出现字符 j 的位置；
     * - 如果 s[i] == j 那么 dp[i][j] = i
     * - 如果 s[i] != j 则 dp[i][j] = dp[i+1][j];
     * 可以看到，遍历的顺序应该从后往前
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length();
        int[][] dp = new int[n + 1][26];
        // 初始值，从后往前遍历，先计算 dp[n-1][j] ，如果后面没有字符 j，只直接定义成 n，作为一个取不到的位置
        Arrays.fill(dp[n], n);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) == j + 'a') {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        String result = "";
        for (String subSeq : dictionary) {
            int m = subSeq.length();
            boolean flag = true;
            for (int r = 0, l = 0; r < m; r++) {
                // 如果没匹配上，直接跳出
                if (dp[l][subSeq.charAt(r) - 'a'] == n) {
                    flag = false;
                    break;
                }
                // 当前字符匹配上了，匹配下一个。s 中匹配字符需要跳到已匹配成功的字符出现位置的后方
                l = dp[l][subSeq.charAt(r) - 'a'] + 1;
            }
            if (flag) {
                if (subSeq.length() > result.length() || (subSeq.length() == result.length() && subSeq.compareTo(result) < 0)) {
                    result = subSeq;
                }
            }
        }
        return result;
    }

    /**
     * 581. 最短无序连续子数组
     * <p>
     * 符合条件的连续子数组中最小值需要大于左边的最后一个数，同时连续子数组中的最小值需要小于右边的第一个数；且，左右两边都要升序
     * <p>
     * 尝试双指针，l = 0,r = n-1;一旦出现 nums[r] < nums[l]，那么区间 [l, r] 必然要重排序
     * <p>
     * 怎么突然感觉双指针从中间开始更好呢，左区间遍历的时候，需要小于左区间的最小值，否则，加入到区间中
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    /**
     * 594. 最长和谐子序列
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        int l = 0, r = 0;
        while (r < n) {
            if (nums[r] - nums[l] == 1) {
                result = Math.max(result, r - l + 1);
            }
            while (nums[r] - nums[l] > 1) {
                l++;
            }
            r++;
        }
        return result;
    }

    public int findLHS2(int[] nums) {
        // <num, count>
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - 1)) {
                result = Math.max(result, map.get(nums[i]) + map.get(nums[i] - 1));
            }
        }
        return result;
    }

    /**
     * 611. 有效三角形的个数
     * 最小的两条边之和大于第三边即可
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        // 这里就可以排个序了
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = i + 2;
            while (r < n) {
                while (r < n && nums[i] + nums[l] > nums[r]) {
                    count++;
                    r++;
                }
                l++;
                // 这里的 r 又折返了，导致时间复杂度增大。其实可以不用回来，看下面的解法3
                r = l + 1;
            }
        }
        return count;
    }

    /**
     * 611. 有效三角形的个数
     * 尝试利用二分
     *
     * @param nums
     * @return
     */
    public int triangleNumber2(int[] nums) {
        // 这里就可以排个序了
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            while (l < n - 1) {
                int r = l + 1;
                // 找到第一个满足 nums[r] >= nums[i] + nums[l] 的 r
                int a = r, b = n - 1;
                while (a < b) {
                    int mid = (b - a >> 1) + a;
                    int midVal = nums[mid];
                    if (midVal < nums[i] + nums[l]) {
                        a = mid + 1;
                    } else {
                        b = mid;
                    }
                }
                if (nums[a] >= nums[i] + nums[l]) {
                    count += a - l - 1;
                } else {
                    count += a - l;
                }
                l++;
            }
        }
        return count;
    }

    /**
     * 611. 有效三角形的个数
     * 最小的两条边之和大于第三边即可
     *
     * @param nums
     * @return
     */
    public int triangleNumber3(int[] nums) {
        // 这里就可以排个序了
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = i + 2;
            while (l < n - 1) {
                while (r < n && nums[i] + nums[l] > nums[r]) {
                    r++;
                }
                // 有可能 r == l
                count += Math.max(r - l - 1, 0);
                l++;
                // 不用再从 l+1 重新取 r 了。
            }
        }
        return count;
    }

    /**
     * 825. 适龄的朋友
     * <p>
     * ages[y] <= 0.5 * ages[x] + 7
     * ages[y] > ages[x]
     * ages[y] > 100 && ages[x] < 100
     * 第三条规则被第二条覆盖了
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length;
        int r = n - 1;
        int l = r - 1;
        int count = 0;
        for (; r >= 0 && ages[r] >= 14; r--) {
            while (l >= 0 && ages[r] >= ages[l] && ages[l] > 0.5 * ages[r] + 7) {
                l--;
            }
            count += (r - l - 1);
        }
        return count;
    }


    /**
     * 881. 救生艇
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int l = 0, r = n - 1;
        int count = 0;
        while (l < r) {
            while (l < r && people[l] + people[r] > limit) {
                r--;
                count++;
            }
            while (l < r && people[l] + people[r] <= limit) {
                r--;
                l++;
                count++;
            }
        }
        return l == r ? count + 1 : count;
    }

    /**
     * 1446. 连续字符
     *
     * @param s
     * @return
     */
    public int maxPower(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int l = 0, r = 1;
        int max = 1;
        while (r < n) {
            while (r < n && chars[r] == chars[l]) {
                r++;
            }
            max = Math.max(max, r - l);
            l = r;
        }
        return max;
    }

    /**
     * 1743. 从相邻元素对还原数组
     * adjacentPairs 中只出现一次的两个数是头尾两个数
     *
     * @param adjacentPairs
     * @return
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        int[] result = new int[n + 1];

        Map<Integer, List<Integer>> map = new HashMap<>(16);
        for (int[] adjacentPair : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<>());
            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() == 1) {
                result[0] = entry.getKey();
                break;
            }
        }
        result[1] = map.get(result[0]).get(0);
        for (int i = 2; i <= n; i++) {
            List<Integer> list = map.get(result[i - 1]);
            if (list.size() == 1) {
                result[i] = list.get(0);
            } else {
                result[i] = list.get(0) == result[i - 2] ? list.get(1) : list.get(0);
            }
        }
        return result;
    }

    /**
     * 1838. 最高频元素的频数
     * <p>
     * 窗口中其他数与最大数的差的和小于 k，那么窗口的长度就是最高频元素的频数
     * 维护 diff 作为元素中其他数与最大数的差的和，窗口边界变动时更新 diff
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        int len = 0;
        int l = 0, r = 1;
        int diff = 0;
        while (r < n) {
            diff += (nums[r] - nums[r - 1]) * (r - l);
            while (diff > k) {
                diff -= (nums[r] - nums[l]);
                l++;
            }
            len = Math.max(r - l + 1, len);
            r++;
        }
        return len;
    }

    /**
     * 1764. 通过连接另一个数组的子数组得到一个数组
     *
     * @param groups
     * @param nums
     * @return
     */
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = nums.length;
        int k = groups.length;
        int count = 0;
        for (int i = 0, j = 0; i < n && j < k; ) {
            if (checkGroup(groups[j], nums, i)) {
                i += groups[j].length;
                j++;
                count++;
            } else {
                i++;
            }
        }
        return k == count;
    }

    private boolean checkGroup(int[] group, int[] nums, int i) {
        int j = 0;
        for (; j < group.length && i < nums.length; j++) {
            if (group[j] != nums[i]) {
                return false;
            } else {
                i++;
            }
        }
        return j == group.length;
    }

    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int maxDiff = -1;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                maxDiff = Math.max(maxDiff, nums[i] - min);
            }
        }
        return maxDiff;
    }


    public static void main(String[] args) {
//        String[] bs = "aaabb".split("b");
//        for (String b : bs) {
//            System.out.println(b);
//        }
//        System.out.println((double) 13 / 4);
        SlidingWindow slidingWindow = new SlidingWindow();
//        slidingWindow.threeSumClosest(new int[]{0, 1, 1, -1, 55}, 3);
//        slidingWindow.compress(new char[]{'a', 'a', 'a', 'b', 'b', 'a', 'a'});
//        slidingWindow.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3);
        slidingWindow.maxFrequency(new int[]{1, 4, 8, 13}, 5);
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
