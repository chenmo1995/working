package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-28 15:06
 */
public class Test122802 {

    // 维护一个字典树，方便字符串前缀匹配（是多个字符串匹配；如果是两个，匹配子串的话，用 KMP）
    class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    private void insert(String word) {
        Trie node = trie;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        // 字符串数组按照字符串长度顺序排列
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            // 长度为 0 的跳过
            if (word.length() == 0) {
                continue;
            }
            // 对每个串都在前缀树中搜查，搜到了就加入结果，没搜到就加入前缀树
            if (dfs(word, 0)) {
                result.add(word);
            } else {
                insert(word);
            }
        }
        return result;
    }

    /**
     * 深度优先遍历前缀树，也就是回溯
     * 从待搜串的第一位开始，匹配到了前缀树的尾部节点（一条支线上，有可能有多个尾部），
     * 就从下一位字符开始继续遍历前缀树（注意使用 startIndex）；
     * 如果匹配不到，有两种可能，一是就是匹配不到，还有一可能是前一个匹配成功的串选择的不对，
     * 需要退回到上一个单词的尾部，从下一位开始，重新遍历前缀树
     *
     * @param word
     * @param startIndex 记录
     * @return
     */
    private boolean dfs(String word, int startIndex) {
        int n = word.length();
        // 终止条件呢？这一层搜索的起始位置是 word.length()，就说明匹配成功了
        if (startIndex == n) {
            return true;
        }
        Trie node = trie;
        for (int i = startIndex; i < n; i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            // 移动指针
            node = node.children[index];
            // 匹配不成功
            if (node == null) {
                return false;
            }
            // 匹配成功则继续匹配下一个字符

            if (node.isEnd) {
                // 匹配到了一个完整的前缀串了，继续匹配待搜串
                if (dfs(word, i + 1)) {
                    return true;
                }
                // 后面匹配不成功，退出这次递归，其实就是回退了。继续找该分支上下一个尾部节点（isEnd == true 的节点）
            }
        }
        // 前缀又匹配到了，又没有到尾部节点，说明只是前缀而已，字符串并不存在于前缀树中
        return false;
    }

    public static void main(String[] args) {
        Test122802 test122802 = new Test122802();
        test122802.findAllConcatenatedWordsInADict(new String[]{"cat","dog","catdog"});

    }
}
